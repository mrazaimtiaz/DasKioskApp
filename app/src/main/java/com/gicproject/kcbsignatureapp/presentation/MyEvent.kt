package com.gicproject.kcbsignatureapp.presentation

import okhttp3.MultipartBody

sealed class MyEvent {
    data class GetEmployeeData(val id: String) : MyEvent()
    object GetEmployeeListData : MyEvent()
    object AddEmployeeData: MyEvent()
    data class CivilIdChanged(val text: String) : MyEvent()
    data class SerialNoChanged(val text: String) : MyEvent()
    data class DOBChanged(val text: String) : MyEvent()
    data class NationalityChanged(val text: String) : MyEvent()
    data class ExpiryDateChanged(val text: String) : MyEvent()
    data class Tel1Changed(val text: String) : MyEvent()
    data class Tel2Changed(val text: String) : MyEvent()
    data class EmailChanged(val text: String) : MyEvent()
    data class FullNameChanged(val text: String) : MyEvent()
    data class FirstNameChanged(val text: String) : MyEvent()
    data class SecondNameChanged(val text: String) : MyEvent()
    data class ThirdNameChanged(val text: String) : MyEvent()
    data class FullNameArChanged(val text: String) : MyEvent()
    data class SecondNameArChanged(val text: String) : MyEvent()
    data class FirstNameArChanged(val text: String) : MyEvent()
    data class ThirdNameArChanged(val text: String) : MyEvent()
    data class GenderChanged(val text: String) : MyEvent()
    data class FullAddressChanged(val text: String) : MyEvent()
    data class BloodGroupChanged(val text: String) : MyEvent()
    data class PassportNoChanged(val text: String) : MyEvent()
    data class OccupationChanged(val text: String) : MyEvent()
}
