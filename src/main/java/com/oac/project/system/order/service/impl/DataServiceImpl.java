package com.oac.project.system.order.service.impl;

import com.oac.project.system.order.mapper.DataMapper;
import com.oac.project.system.order.service.DataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DataServiceImpl implements DataService {

    @Resource
    private DataMapper dataMapper;

    /**
     * 获取本年订单总金额
     * @return
     */
    @Override
    public Long getYearSumMoney() {
        Long yearSumMoney = dataMapper.getYearSumMoney();
        if(yearSumMoney == null){
            return 0L;
        }else {
            return yearSumMoney;
        }
    }

    /**
     * 获取去年订单金额
     * @return
     */
    @Override
    public Long getLastYearSumMoney() {
        Long lastYearSumMoney = dataMapper.getLastYearSumMoney();
        if(lastYearSumMoney == null){
            return 0L;
        }else {
            return lastYearSumMoney;
        }
    }

    /**
     * 获取本月收账金额
     * @return
     */
    @Override
    public Long getMonthSumMoney() {
        Long monthSumMoney = dataMapper.getMonthSumMoney();
        if(monthSumMoney == null){
            return 0L;
        }else {
            return monthSumMoney;
        }
    }

    /**
     * 获取上月收账金额
     * @return
     */
    @Override
    public Long getLstMonthSumMoney() {
        Long lstMonthSumMoney = dataMapper.getLstMonthSumMoney();
        if(lstMonthSumMoney == null){
            return 0L;
        }else {
            return lstMonthSumMoney;
        }
    }

    /**
     * 获取本年支出
     * @return
     */
    @Override
    public Long getYearPaySumMoney() {
        Long yearPaySumMoneyBoard = dataMapper.getYearPaySumMoneyBoard();
        Long yearPaySumMoneyBatten = dataMapper.getYearPaySumMoneyBatten();
        if(yearPaySumMoneyBatten == null && yearPaySumMoneyBoard == null){
            return 0L;
        }else if (yearPaySumMoneyBatten !=null && yearPaySumMoneyBoard == null){
            return yearPaySumMoneyBatten;
        }else if (yearPaySumMoneyBatten ==null && yearPaySumMoneyBoard != null){
            return yearPaySumMoneyBoard;
        }else if (yearPaySumMoneyBatten !=null && yearPaySumMoneyBoard != null){
            return yearPaySumMoneyBatten + yearPaySumMoneyBoard;
        }else {
            return null;
        }
    }

    /**
     * 获取去年支出
     * @return
     */
    @Override
    public Long getLastYearPaySumMoney() {
        Long lastYearPaySumMoneyBoard = dataMapper.getLastYearPaySumMoneyBoard();
        Long lastYearPaySumMoneyBatten = dataMapper.getLastYearPaySumMoneyBatten();
        if(lastYearPaySumMoneyBatten == null && lastYearPaySumMoneyBoard == null){
            return 0L;
        }else if (lastYearPaySumMoneyBatten !=null && lastYearPaySumMoneyBoard == null){
            return lastYearPaySumMoneyBatten;
        }else if (lastYearPaySumMoneyBatten ==null && lastYearPaySumMoneyBoard != null){
            return lastYearPaySumMoneyBoard;
        }else if (lastYearPaySumMoneyBatten !=null && lastYearPaySumMoneyBoard != null){
            return lastYearPaySumMoneyBatten + lastYearPaySumMoneyBoard;
        }else {
            return null;
        }
    }

    /**
     * 获取本月支出
     * @return
     */
    @Override
    public Long getMonthPaySumMoney() {
        Long monthPaySumMoneyBatten = dataMapper.getMonthPaySumMoneyBatten();
        Long monthPaySumMoneyBoard = dataMapper.getMonthPaySumMoneyBoard();
        if(monthPaySumMoneyBatten == null && monthPaySumMoneyBoard == null){
            return 0L;
        }else if (monthPaySumMoneyBatten !=null && monthPaySumMoneyBoard == null){
            return monthPaySumMoneyBatten;
        }else if (monthPaySumMoneyBatten ==null && monthPaySumMoneyBoard != null){
            return monthPaySumMoneyBoard;
        }else if (monthPaySumMoneyBatten !=null && monthPaySumMoneyBoard != null){
            return monthPaySumMoneyBatten + monthPaySumMoneyBoard;
        }else {
            return null;
        }
    }

    /**
     * 获取上月支出
     * @return
     */
    @Override
    public Long getLastMonthPaySumMoney() {
        Long lastMonthPaySumMoneyBatten = dataMapper.getLastMonthPaySumMoneyBatten();
        Long lastMonthPaySumMoneyBoard = dataMapper.getLastMonthPaySumMoneyBoard();
        if(lastMonthPaySumMoneyBatten == null && lastMonthPaySumMoneyBoard == null){
            return 0L;
        }else if (lastMonthPaySumMoneyBatten !=null && lastMonthPaySumMoneyBoard == null){
            return lastMonthPaySumMoneyBatten;
        }else if (lastMonthPaySumMoneyBatten ==null && lastMonthPaySumMoneyBoard != null){
            return lastMonthPaySumMoneyBoard;
        }else if (lastMonthPaySumMoneyBatten !=null && lastMonthPaySumMoneyBoard != null){
            return lastMonthPaySumMoneyBatten + lastMonthPaySumMoneyBoard;
        }else {
            return null;
        }
    }

}
