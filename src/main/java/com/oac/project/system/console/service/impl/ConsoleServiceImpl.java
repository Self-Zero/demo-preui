package com.oac.project.system.console.service.impl;

import com.oac.project.system.city.domain.City;
import com.oac.project.system.city.mapper.CityMapper;
import com.oac.project.system.console.service.ConsoleService;
import com.oac.project.system.console.vo.ShowEcharts;
import com.oac.project.system.console.vo.ShowMoneys;
import com.oac.project.system.customer.domain.Customer;
import com.oac.project.system.customer.mapper.CustomerMapper;
import com.oac.project.system.mode.domain.Mode;
import com.oac.project.system.mode.mapper.ModeMapper;
import com.oac.project.system.order.domain.MoneyUtil;
import com.oac.project.system.order.domain.MoneyVO;
import com.oac.project.system.order.mapper.DataMapper;
import com.oac.project.system.order.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * ConsoleService实现类
 */
@Service
public class ConsoleServiceImpl implements ConsoleService {

    @Resource
    private CustomerMapper customerMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private CityMapper cityMapper;

    /**
     * Echarts图表数据展示 CustomerName and Customer-Order-Count
     *
     * @return
     */
    @Override
    public List<ShowEcharts> getCustomerAndOrderCount() {
        // 定义图表信息展示集合的实例
        List<ShowEcharts> showEchartss = new ArrayList<>();
        // 查询全部的客户信息
        List<Customer> allCustomer = customerMapper.getAllCustomer(null, null, null, null);
        // 查询全部的城市信息
        List<City> allCity = cityMapper.getAllCity();
        // 声明 ShowEcharts对象
        ShowEcharts showEcharts = null;
        // 声明count条数
        Long count = null;
        // 声明StringBuffer对量
        StringBuffer buffer = null;
        // 遍历全部的客户信息
        for (Customer customer : allCustomer) {
            // 创建buffer对象的引用
            buffer = new StringBuffer();
            // 创建showEcharts对象的引用
            showEcharts = new ShowEcharts();
            // 根据客户的id查询用户的全部订单条数
            count = orderMapper.getCount(null, null, customer.getCustomerId(), null, null);
            // 将count条数添加至showEcharts对象的引用中
            showEcharts.setOrderCount(count);
            // 遍历城市的信息
            for (City city : allCity) {
                // 比较客户的城市id和城市的id
                if (customer.getAddressId().equals(city.getCityId())) {
                    // 将二级城市的名字追加至 StringBuffer 中
                    buffer.append(city.getCityName());
                    // 在将客户的名称追加至 StringBuffer 中
                    buffer.append("-" + customer.getCustomerName());
                    // 将追加好的StringBuffer添加至showEcharts对象的引用中
                    showEcharts.setCustomerName(buffer);
                }
            }
            // 将对象的引用添加至图表信息展示的集合中
            showEchartss.add(showEcharts);
        }
        // 返回数据
        return showEchartss;
    }

    @Resource
    private ModeMapper modeMapper;
    @Resource
    private DataMapper dataMapper;

    /**
     * 查询饼状图中的数据 每种类型的收入
     *
     * @return
     */
    @Override
    public List<ShowMoneys> getOrderIncomeByMode() {
        List<ShowMoneys> showEchartss = new ArrayList<>();
        ShowMoneys showMoneys = null;
        List<Mode> allMode = modeMapper.getAllMode();
        for (Mode mode : allMode) {
            showMoneys = new ShowMoneys();
            Long money = dataMapper.getCountMoneyByMode(mode.getModeId());
            showMoneys.setMoney(money);
            showMoneys.setName(mode.getModeName());
            showEchartss.add(showMoneys);
        }
        return showEchartss;
    }

    /**
     * 查询本年每个月的收入情况
     *
     * @return
     */
    @Override
    public List<MoneyVO> getMoneyByMonth() {
        List<MoneyVO> list = new ArrayList<>();
        MoneyVO vo = null;
        List<MoneyUtil> moneyByMonth = dataMapper.getMoneyByMonth();
        List<MoneyUtil> battenByMonth = dataMapper.getBattenByMonth();
        List<MoneyUtil> boardByMonth = dataMapper.getBoardByMonth();
        for (int i = 0;i<12;i++){
            vo = new MoneyVO();
            switch (moneyByMonth.get(i).getMonth()){
                case 1:
                    vo.setMonth("一月");
                    break;
                case 2:
                    vo.setMonth("二月");
                    break;
                case 3:
                    vo.setMonth("三月");
                    break;
                case 4:
                    vo.setMonth("四月");
                    break;
                case 5:
                    vo.setMonth("五月");
                    break;
                case 6:
                    vo.setMonth("六月");
                    break;
                case 7:
                    vo.setMonth("七月");
                    break;
                case 8:
                    vo.setMonth("八月");
                    break;
                case 9:
                    vo.setMonth("九月");
                    break;
                case 10:
                    vo.setMonth("十月");
                    break;
                case 11:
                    vo.setMonth("十一月");
                    break;
                case 12:
                    vo.setMonth("十二月");
                    break;
            }
            vo.setMoney(moneyByMonth.get(i).getMoney().longValue());
            vo.setPay(battenByMonth.get(i).getMoney()+boardByMonth.get(i).getMoney().longValue());
            list.add(vo);
        }
        return list;
    }
}
