package com.alxena.pronunciationtrainer.data.util

import com.alxena.pronunciationtrainer.data.model.TopGradeDAO

class LessonCategory(val title:String, val difficulty:Int, val numColumn: Int, val lessons:List<TopGradeDAO>) {
}