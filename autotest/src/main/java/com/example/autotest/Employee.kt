package com.example.autotest

/**
 *Author: zbt
 *Time: 2021/6/20 21:07
 *Description: This is Emplyee
 */
class Employee {
    /** 初始需求 **/
    lateinit var name: String
    lateinit var employeeId: String
}

class FinanceDepartment {
    /**
     * 计算员工薪水
     */
    fun calculateSalary(employee: Employee): Double {
        return 10000.0
    }
}

class HRDepartment {
    /**
     * 员工是否能升级
     */
    fun canPromoteThisYear(employee: Employee): Boolean {
        return true
    }
}