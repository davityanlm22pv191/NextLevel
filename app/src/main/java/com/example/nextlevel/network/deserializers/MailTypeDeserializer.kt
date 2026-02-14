package com.example.nextlevel.network.deserializers

import com.example.nextlevel.data.mail.model.MailType
import com.example.nextlevel.data.mail.model.MailType.GIFT
import com.example.nextlevel.data.mail.model.MailType.NOTIFICATION
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class MailTypeDeserializer: JsonDeserializer<MailType> {

	override fun deserialize(
		json: JsonElement?,
		typeOfT: Type?,
		context: JsonDeserializationContext?
	): MailType {
		return when(json?.asString?.lowercase()) {
			"notification" -> NOTIFICATION
			"gift" -> GIFT
			else -> error("Unknown mail type")
		}
	}
}