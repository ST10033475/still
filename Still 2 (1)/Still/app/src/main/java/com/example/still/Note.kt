package com.example.still

import android.widget.EditText
import java.util.UUID

data class Note(
    var uuid: String = "",
    var id: String = "",
    var title: String = "",
    var description: String = "",
    var date: String = ""
)