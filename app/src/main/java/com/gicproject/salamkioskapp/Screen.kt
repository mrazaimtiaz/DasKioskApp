package com.gicproject.salamkioskapp

sealed class Screen(val route: String){
    object SettingScreen: Screen("setting_screen")
    object SelectOptionScreen: Screen("select_option_screen")
    object SelectDoctorScreen: Screen("select_doctor_screen")
    object SelectDepartmentScreen: Screen("select_department_screen")
    object InsertKnetScreen: Screen("insert_knet_screen")
    object DoctorPayScreen: Screen("doctor_pay_screen")
    object InsertCivilIdScreen: Screen("insert_civilid_screen")
    object SelectServiceScreen: Screen("select_service_screen")
    object SelectSingleServiceScreen: Screen("select_single_service_screen")
    object SelectTestServiceScreen: Screen("select_test_service_screen")
    object SelectChildTestServiceScreen: Screen("select_child_test_service_screen")
    object AppointmentInfoScreen: Screen("appointment_info_screen")
    object ConsultVisitScreen: Screen("consult_visit_screen")
    object CreateInvoiceScreen: Screen("create_invoice_screen")
    object LinkPayScreen: Screen("link_pay_screen")
}
