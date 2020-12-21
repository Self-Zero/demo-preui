package com.oac.common;

import com.oac.project.system.order.domain.OrderInfo;
import com.oac.project.system.order.mapper.OrderMapper;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class random {

    @Resource
    private static OrderMapper orderMapper;

    public static void main(String[] args){

        for(int i = 0;i<Long.MAX_VALUE;i++){

        }

        random r = new random();
        for (int i =0;i<1;i++){
            OrderInfo orderInfo = new OrderInfo();
            String add = null;
            int g = (int) (Math.random() * 20) + 1;
            switch (g) {
                case 1:
                    add = "塞纳风光";
                    break;
                case 2:
                    add = "爱琴堡";
                    break;
                case 3:
                    add = "森和阳光小区";
                    break;
                case 4:
                    add = "京林公园小区";
                    break;
                case 5:
                    add = "福慧安家园";
                    break;
                case 6:
                    add = "左岸汀芷";
                    break;
                case 7:
                    add = "奥林水榭";
                    break;
                case 8:
                    add = "水岸林邸";
                    break;
                case 9:
                    add = "吴越天下";
                    break;
                case 10:
                    add = "蜀韵雅居";
                    break;
                case 11:
                    add = "锦绣湖豪景苑";
                    break;
                case 12:
                    add = "皇朝苑";
                    break;
                case 13:
                    add = "皇都花园";
                    break;
                case 14:
                    add = "丽高王府";
                    break;
                case 15:
                    add = "名仁家园";
                    break;
                case 16:
                    add = "金玉良苑";
                    break;
                case 17:
                    add = "泰华豪园";
                    break;
                case 18:
                    add = "罗马佳苑";
                    break;
                case 19:
                    add = "唐御康城";
                    break;
                case 20:
                    add = "万科星园";
                    break;
            }
            StringBuffer orderName = new StringBuffer();
            orderName.append(add);
            int a = (int) (Math.random() * 25) + 1;     // 25号楼
            orderName.append("-" + a + "号楼");
            int b = (int) (Math.random() * 5) + 1;      // 5个单元
            orderName.append("-" + b + "单元");
            int c = (int) (Math.random() * 35) + 1;     // 35层
            orderName.append("-" + c);
            int d = (int) (Math.random() * 4) + 1;      // 4户
            orderName.append("0" + d);

            Integer customerId = (int) (Math.random() * 8) + 1;               // 客户id  --  addressid
            Long addressId = null;
            switch (customerId) {
                case 1:
                    addressId = 4L;
                    break;
                case 2:
                    addressId = 5L;
                    break;
                case 3:
                    addressId = 6L;
                    break;
                case 4:
                    addressId = 8L;
                    break;
                case 5:
                    addressId = 9L;
                    break;
                case 6:
                    addressId = 10L;
                    break;
                case 7:
                    addressId = 7L;
                    break;
                case 8:
                    addressId = 5L;
                    break;
            }

            Integer materialId = (int) (Math.random() * 4) + 1;               // 材质id

            Integer orderNumber = (int) (Math.random() * 5) + 16;             // 步数

            StringBuffer orderColor = new StringBuffer();
            Integer ag = (int) (Math.random() * 21) + 1;                      // 订单颜色
            orderColor.append("S-" + ag);

            Integer battenVersionId = (int) (Math.random() * 7) + 1;          // 柱子型号

            Integer acsd = (int) (Math.random() * 4) + 1;                       // 柱子型号
            orderInfo.setModeId(acsd.longValue());

            Integer battenBigId = (int) (Math.random() * 3) + 7;              // 大柱规格

            Integer battenSmallId = (int) (Math.random() * 6) + 1;            // 小柱规格

            Integer orderPrice = (int) (Math.random() * 1000) + 2000;         // 订单定金

            Integer orderTotal = (int) (Math.random() * 4000) + 6000;         // 订单总金额

            Integer orderState = (int) (Math.random() * 2);                   // 订单状态 0-1
            orderInfo.setAddressId(addressId);
            orderInfo.setOrderName(orderName.toString());
            orderInfo.setCustomerId(customerId.longValue());
            orderInfo.setMaterialId(materialId.longValue());
            orderInfo.setOrderNumber(orderNumber.toString());
            orderInfo.setOrderColor(orderColor.toString());
            orderInfo.setBattenVersionId(battenVersionId.longValue());
            orderInfo.setBattenBigId(battenBigId.longValue());
            orderInfo.setBattenSmallId(battenSmallId.longValue());
            orderInfo.setOrderPrice(orderPrice.longValue());
            orderInfo.setOrderTotal(orderTotal.longValue());
            orderInfo.setOrderState(orderState);
            Integer ac = (int) (Math.random() * 12)+1;
            Integer ad = (int) (Math.random() * 28)+1;
            StringBuffer acs = new StringBuffer();
            acs.append("2020-"+ac+"-"+ad);
            System.out.println(acs);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                orderInfo.setCreated(simpleDateFormat.parse(acs.toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Integer integer = orderMapper.saveOrderInfo(orderInfo);
        }
    }



    public void a(){

    }

}

