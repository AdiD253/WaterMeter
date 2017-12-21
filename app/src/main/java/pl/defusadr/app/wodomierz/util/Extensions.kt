package pl.defusadr.app.wodomierz.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

infix fun ViewGroup.inflate(layoutResId: Int): View =
        LayoutInflater.from(context).inflate(layoutResId, this, false)
