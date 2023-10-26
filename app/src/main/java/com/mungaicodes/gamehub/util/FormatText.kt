package com.mungaicodes.gamehub.util

import org.jsoup.Jsoup

fun String.parseHtml(): String {
    return Jsoup.parse(this).text()
}