package edu.vt.mobiledev.aacapplication.util

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.Locale

object TTS {
    private var tts: TextToSpeech? = null

    fun init(context: Context) {
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts?.language = Locale.KOREAN
            }
        }
    }

    fun speak(text: String) {
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "tts1")
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
    }
}