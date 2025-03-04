package cz.mendelu.xzirchuk.project.ui.screens.textRecognition

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.languageid.LanguageIdentification
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import cz.mendelu.xzirchuk.project.R
import cz.mendelu.xzirchuk.project.architecture.BaseViewModel
import cz.mendelu.xzirchuk.project.ui.UiState
import java.util.Locale

class TextRecognitionViewModel :BaseViewModel(){


    var uiState  = mutableStateOf(
        UiState<String,Int>(
        loading = false,
        data = "Translation",
        errors = null
    )
    )
    var targetLang:String=""
    var sourceLang:String=""

    fun translateText(text:String,onFinish:()->Unit) {

            val languageIdentifier = LanguageIdentification.getClient()

            languageIdentifier.identifyLanguage(text)
                .addOnSuccessListener { languageCode ->
                    if (languageCode == "und") {
                        uiState.value = UiState(
                            loading = false,
                            data = "Translate",
                            errors = R.string.something_went_wrong
                        )
                    } else {
                        sourceLang = languageCode
                        Log.d("bruh", sourceLang)

                        targetLang = Locale.getDefault()
                            .toLanguageTag()
                            .split(Regex("-")).get(0)

                        val options = TranslatorOptions.Builder()
                            .setSourceLanguage(sourceLang)
                            .setTargetLanguage(targetLang)
                            .build()

                        val translator = Translation.getClient(options)

                        val conditions = DownloadConditions.Builder()
                            .requireWifi()
                            .build()
                        uiState.value = UiState(
                            loading = true,
                            data = "Translation",
                            errors = null
                        )
                        onFinish()
                        translator.downloadModelIfNeeded(conditions)
                            .addOnSuccessListener {

                                Log.d("bruh", "suc")
                                // Model downloaded successfully. Okay to start translating.
                                // (Set a flag, unhide the translation UI, etc.)
                                translator.translate(text)
                                    .addOnSuccessListener { translatedText ->
                                        Log.d("bruh", translatedText)
                                        uiState.value = UiState(
                                            loading = false,
                                            data = translatedText,
                                            errors = null
                                        )
                                        Log.d("bruh", uiState.value.toString())
                                        onFinish()

                                    }
                                    .addOnFailureListener { exception ->
                                        // Error.
                                        // ...
                                        uiState.value = UiState(
                                            loading = false,
                                            data = "Translate",
                                            errors = R.string.something_went_wrong
                                        )
                                        Log.d("bruh", uiState.value.toString())
                                        onFinish()
                                        Log.d("bruh", exception.toString())
                                        translator.close()
                                        onFinish()

                                    }
                            }
                            .addOnFailureListener { exception ->
                                uiState.value = UiState(
                                    loading = false,
                                    data = "Translate",
                                    errors = R.string.something_went_wrong
                                )
                                Log.d("bruh", exception.toString())
                                onFinish()

                                // Model couldn’t be downloaded or other internal error.
                                // ...
                            }


                    }
                }
                .addOnFailureListener {
                    uiState.value = UiState(
                        loading = false,
                        data = "Translate",
                        errors = R.string.something_went_wrong
                    )
                    onFinish()

                    // Model couldn’t be loaded or other internal error.
                    // ...
                }
            onFinish()

//
//
//


        }

}
