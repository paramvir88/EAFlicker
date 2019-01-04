package com.eaflicker.paramvir.eaflicker

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey


/**
 * "id":"46529116962",
"owner":"160158832@N05",
"secret":"1b3c7b8820",
"server":"7925",
"farm":8,
"title":"Star Chef: Cooking & Restaurant Game MOD Apk [Unlimited Money] v2.25 Android Download by 99Games",
"ispublic":1,
"isfriend":0,
"isfamily":0,
"url_o":"https:\/\/farm8.staticflickr.com\/7925\/46529116962_364f430e03_o.png",
"height_o":"349",
"width_o":"884"
 */

@Entity
data class Photo(@PrimaryKey var id: String) {

    var owner: String? = ""
    var secret: String? = ""
    var title: String? = ""
    var url_o: String? = ""
    var searchKey: String = ""


}
