package com.example.stablestable.data.classes


@Suppress("UNCHECKED_CAST")
data class ContactInfo(
    val email: String,
    val phone: String
){
    companion object {
        fun fromFbData(map:Map<String,Any?>) = object{
            val raw = map["contact-information"] as Map<String, String>

            val email by raw
            val phone by raw

            val data = ContactInfo(email,phone)

        }.data
    }
}
