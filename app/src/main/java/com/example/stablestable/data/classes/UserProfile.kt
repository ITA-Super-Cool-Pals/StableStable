package com.example.stablestable.data.classes

data class UserProfile(
    val id: String,
    val contactInfo: ContactInfo,
    val firstName: String,
    val lastName: String,
    val stableID: String
){
    val fullName: String = "$firstName $lastName"

    companion object {
        fun fromFbData(map:Map<String, Any?>) = object {
            val firstname by map
            val lastname by map
            val contact_information = map["contact-information"] as ContactInfo
            val stable_id = map["stable-id"]

            //val data = UserProfile()


        }
    }




}
