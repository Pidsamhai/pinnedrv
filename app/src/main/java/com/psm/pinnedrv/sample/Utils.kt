package com.psm.pinnedrv.sample

import com.psm.pinnedrv.DataSet
import kotlin.random.Random

private const val cover = ""

fun getMockData() = listOf<DataSet>(
    Category(1, "Science"),
    Product(1, "S1", cover, Random.nextDouble(1.0, 9999999.99).toFloat()),
    Product(2, "S2", cover, Random.nextDouble(1.0, 9999999.99).toFloat()),
    Product(3, "S3", cover, Random.nextDouble(1.0, 9999999.99).toFloat()),
    Product(4, "S4", cover, Random.nextDouble(1.0, 9999999.99).toFloat()),
    Product(5, "S5", cover, Random.nextDouble(1.0, 9999999.99).toFloat()),

    Category(2, "Physic"),
    Product(6, "P6", cover, Random.nextDouble(1.0, 9999999.99).toFloat()),
    Product(7, "P7", cover, Random.nextDouble(1.0, 9999999.99).toFloat()),
    Product(8, "P8", cover, Random.nextDouble(1.0, 9999999.99).toFloat()),
    Product(9, "P9", cover, Random.nextDouble(1.0, 9999999.99).toFloat()),
    Product(10, "P10", cover, Random.nextDouble(1.0, 9999999.99).toFloat()),

    Category(3, "Math"),
    Product(11, "M11", cover, Random.nextDouble(1.0, 9999999.99).toFloat()),
    Product(12, "M12", cover, Random.nextDouble(1.0, 9999999.99).toFloat()),
    Product(13, "M13", cover, Random.nextDouble(1.0, 9999999.99).toFloat()),
    Product(14, "M14", cover, Random.nextDouble(1.0, 9999999.99).toFloat()),
    Product(15, "M15", cover, Random.nextDouble(1.0, 9999999.99).toFloat()),

    Category(4, "Sport"),
    Product(16, "S16", cover, Random.nextDouble(1.0, 9999999.99).toFloat()),
    Product(17, "S17", cover, Random.nextDouble(1.0, 9999999.99).toFloat()),
    Product(18, "S18", cover, Random.nextDouble(1.0, 9999999.99).toFloat()),
    Product(19, "S19", cover, Random.nextDouble(1.0, 9999999.99).toFloat()),
    Product(20, "S20", cover, Random.nextDouble(1.0, 9999999.99).toFloat()),

    Category(5, "English"),
    Product(21, "E16", cover, Random.nextDouble(1.0, 9999999.99).toFloat()),
    Product(22, "E17", cover, Random.nextDouble(1.0, 9999999.99).toFloat()),
    Product(23, "E23", cover, Random.nextDouble(1.0, 9999999.99).toFloat()),
    Product(24, "E24", cover, Random.nextDouble(1.0, 9999999.99).toFloat()),
    Product(25, "E25", cover, Random.nextDouble(1.0, 9999999.99).toFloat()),

    Category(6, "Art"),
    Product(26, "A26", cover, Random.nextDouble(1.0, 9999999.99).toFloat()),
    Product(27, "A27", cover, Random.nextDouble(1.0, 9999999.99).toFloat()),
    Product(28, "A28", cover, Random.nextDouble(1.0, 9999999.99).toFloat()),
    Product(29, "A29", cover, Random.nextDouble(1.0, 9999999.99).toFloat()),
    Product(30, "A30", cover, Random.nextDouble(1.0, 9999999.99).toFloat()),
)