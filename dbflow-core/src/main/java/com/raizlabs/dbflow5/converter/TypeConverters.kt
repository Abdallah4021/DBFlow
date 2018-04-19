package com.raizlabs.dbflow5.converter

import java.math.BigDecimal
import java.math.BigInteger
import java.sql.Time
import java.sql.Timestamp
import java.util.*

/**
 * Description: Defines how we store and retrieve a [java.math.BigDecimal]
 */
class BigDecimalConverter : TypeConverter<String, BigDecimal>() {
    override fun getDBValue(model: BigDecimal?): String? = model?.toString()

    override fun getModelValue(data: String?): BigDecimal? = if (data == null) null else BigDecimal(data)
}

/**
 * Description: Defines how we store and retrieve a [java.math.BigInteger]
 */
class BigIntegerConverter : TypeConverter<String, BigInteger>() {
    override fun getDBValue(model: BigInteger?): String? = model?.toString()

    override fun getModelValue(data: String?): BigInteger? = if (data == null) null else BigInteger(data)
}

/**
 * Description: Defines how we store and retrieve a [java.util.Calendar]
 */
@com.raizlabs.dbflow5.annotation.TypeConverter(allowedSubtypes = [(GregorianCalendar::class)])
class CalendarConverter : TypeConverter<Long, Calendar>() {

    override fun getDBValue(model: Calendar?): Long? = model?.timeInMillis

    override fun getModelValue(data: Long?): Calendar? =
        if (data != null) Calendar.getInstance().apply { timeInMillis = data } else null
}

/**
 * Description: Defines how we store and retrieve a [java.util.Date]
 */
class DateConverter : TypeConverter<Long, Date>() {

    override fun getDBValue(model: Date?): Long? = model?.time

    override fun getModelValue(data: Long?): Date? = if (data == null) null else Date(data)
}

/**
 * Description: Defines how we store and retrieve a [java.sql.Date]
 */
@com.raizlabs.dbflow5.annotation.TypeConverter(allowedSubtypes = [(Time::class), (Timestamp::class)])
class SqlDateConverter : TypeConverter<Long, java.sql.Date>() {

    override fun getDBValue(model: java.sql.Date?): Long? = model?.time

    override fun getModelValue(data: Long?): java.sql.Date? = if (data == null) null else java.sql.Date(data)
}

/**
 * Description: Responsible for converting a [UUID] to a [String].
 *
 * @author Andrew Grosner (fuzz)
 */
class UUIDConverter : TypeConverter<String, UUID>() {

    override fun getDBValue(model: UUID?): String? = model?.toString()

    override fun getModelValue(data: String?): UUID? {
        return if (data == null) {
            null
        } else UUID.fromString(data)
    }
}