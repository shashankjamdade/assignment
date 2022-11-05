package com.demoproj.ui.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.mvvmwithhilt.module.dto.FilterRes
import com.demoproj.R
import com.demoproj.viewmodel.HomeScreenViewModel
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity()  {

    var mFilterres: FilterRes? = null
    var mViewModel: HomeScreenViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_splash_screen)
        var res = "{\n" +
                "  \"status\": \"Success\",\n" +
                "  \"message\": \"Data Available\",\n" +
                "  \"errorCode\": \"L128\",\n" +
                "  \"filterData\": [\n" +
                "    {\n" +
                "      \"Cif\": \"7016360\",\n" +
                "      \"companyName\": \"PHARMA ZONE GENERAL CO\",\n" +
                "      \"hierarchy\": [\n" +
                "        {\n" +
                "          \"accountNumber\": \"023170163600014402001\",\n" +
                "          \"brandNameList\": [\n" +
                "            {\n" +
                "              \"brandName\": \"DELTAMED KHAITAN PH\",\n" +
                "              \"locationNameList\": [\n" +
                "                {\n" +
                "                  \"locationName\": \"KHAITAN\",\n" +
                "                  \"merchantNumber\": [\n" +
                "                    {\n" +
                "                      \"mid\": \"354902077\",\n" +
                "                      \"outletNumber\": [\n" +
                "                        \"12764173\"\n" +
                "                      ]\n" +
                "                    },\n" +
                "                    {\n" +
                "                      \"mid\": \"354902078\",\n" +
                "                      \"outletNumber\": [\n" +
                "                        \"12764174\"\n" +
                "                      ]\n" +
                "                    }\n" +
                "                  ]\n" +
                "                }\n" +
                "              ]\n" +
                "            },\n" +
                "            {\n" +
                "              \"brandName\": \"FARMA ZONE PHARMACY\",\n" +
                "              \"locationNameList\": [\n" +
                "                {\n" +
                "                  \"locationName\": \"HAWALLY\",\n" +
                "                  \"merchantNumber\": [\n" +
                "                    {\n" +
                "                      \"mid\": \"354902033\",\n" +
                "                      \"outletNumber\": [\n" +
                "                        \"29910294\"\n" +
                "                      ]\n" +
                "                    },\n" +
                "                    {\n" +
                "                      \"mid\": \"354902066\",\n" +
                "                      \"outletNumber\": [\n" +
                "                        \"12764162\"\n" +
                "                      ]\n" +
                "                    },\n" +
                "                    {\n" +
                "                      \"mid\": \"354902079\",\n" +
                "                      \"outletNumber\": [\n" +
                "                        \"28800053\"\n" +
                "                      ]\n" +
                "                    }\n" +
                "                  ]\n" +
                "                },\n" +
                "                {\n" +
                "                  \"locationName\": \"SALMIYA\",\n" +
                "                  \"merchantNumber\": [\n" +
                "                    {\n" +
                "                      \"mid\": \"354902120\",\n" +
                "                      \"outletNumber\": [\n" +
                "                        \"29905829\"\n" +
                "                      ]\n" +
                "                    },\n" +
                "                    {\n" +
                "                      \"mid\": \"354902098\",\n" +
                "                      \"outletNumber\": [\n" +
                "                        \"44000645\"\n" +
                "                      ]\n" +
                "                    }\n" +
                "                  ]\n" +
                "                },\n" +
                "                {\n" +
                "                  \"locationName\": \"MIDAN HAWALLY\",\n" +
                "                  \"merchantNumber\": [\n" +
                "                    {\n" +
                "                      \"mid\": \"354902149\",\n" +
                "                      \"outletNumber\": [\n" +
                "                        \"29916071\"\n" +
                "                      ]\n" +
                "                    }\n" +
                "                  ]\n" +
                "                },\n" +
                "                {\n" +
                "                  \"locationName\": \"ALJAHRA\",\n" +
                "                  \"merchantNumber\": [\n" +
                "                    {\n" +
                "                      \"mid\": \"354902011\",\n" +
                "                      \"outletNumber\": [\n" +
                "                        \"29911084\"\n" +
                "                      ]\n" +
                "                    }\n" +
                "                  ]\n" +
                "                },\n" +
                "                {\n" +
                "                  \"locationName\": \"JABRIYA\",\n" +
                "                  \"merchantNumber\": [\n" +
                "                    {\n" +
                "                      \"mid\": \"354902132\",\n" +
                "                      \"outletNumber\": [\n" +
                "                        \"29911092\"\n" +
                "                      ]\n" +
                "                    },\n" +
                "                    {\n" +
                "                      \"mid\": \"354902133\",\n" +
                "                      \"outletNumber\": [\n" +
                "                        \"29911093\"\n" +
                "                      ]\n" +
                "                    },\n" +
                "                    {\n" +
                "                      \"mid\": \"354902128\",\n" +
                "                      \"outletNumber\": [\n" +
                "                        \"29907443\"\n" +
                "                      ]\n" +
                "                    },\n" +
                "                    {\n" +
                "                      \"mid\": \"354902127\",\n" +
                "                      \"outletNumber\": [\n" +
                "                        \"29907442\"\n" +
                "                      ]\n" +
                "                    }\n" +
                "                  ]\n" +
                "                },\n" +
                "                {\n" +
                "                  \"locationName\": \"ALSALAM\",\n" +
                "                  \"merchantNumber\": [\n" +
                "                    {\n" +
                "                      \"mid\": \"354902016\",\n" +
                "                      \"outletNumber\": [\n" +
                "                        \"29800218\"\n" +
                "                      ]\n" +
                "                    },\n" +
                "                    {\n" +
                "                      \"mid\": \"354902017\",\n" +
                "                      \"outletNumber\": [\n" +
                "                        \"29800227\"\n" +
                "                      ]\n" +
                "                    }\n" +
                "                  ]\n" +
                "                },\n" +
                "                {\n" +
                "                  \"locationName\": \"HATEEN\",\n" +
                "                  \"merchantNumber\": [\n" +
                "                    {\n" +
                "                      \"mid\": \"354902043\",\n" +
                "                      \"outletNumber\": [\n" +
                "                        \"29804811\"\n" +
                "                      ]\n" +
                "                    },\n" +
                "                    {\n" +
                "                      \"mid\": \"354902018\",\n" +
                "                      \"outletNumber\": [\n" +
                "                        \"29800220\"\n" +
                "                      ]\n" +
                "                    }\n" +
                "                  ]\n" +
                "                }\n" +
                "              ]\n" +
                "            },\n" +
                "            {\n" +
                "              \"brandName\": \"TAD KHAITAN PH\",\n" +
                "              \"locationNameList\": [\n" +
                "                {\n" +
                "                  \"locationName\": \"KHAI̥TAN\",\n" +
                "                  \"merchantNumber\": [\n" +
                "                    {\n" +
                "                      \"mid\": \"354902076\",\n" +
                "                      \"outletNumber\": [\n" +
                "                        \"12764172\"\n" +
                "                      ]\n" +
                "                    },\n" +
                "                    {\n" +
                "                      \"mid\": \"354902075\",\n" +
                "                      \"outletNumber\": [\n" +
                "                        \"12764171\"\n" +
                "                      ]\n" +
                "                    }\n" +
                "                  ]\n" +
                "                }\n" +
                "              ]\n" +
                "            },\n" +
                "            {\n" +
                "              \"brandName\": \"SHOHADA PH\",\n" +
                "              \"locationNameList\": [\n" +
                "                {\n" +
                "                  \"locationName\": \"KHAITAN\",\n" +
                "                  \"merchantNumber\": [\n" +
                "                    {\n" +
                "                      \"mid\": \"354902074\",\n" +
                "                      \"outletNumber\": [\n" +
                "                        \"12764170\"\n" +
                "                      ]\n" +
                "                    }\n" +
                "                  ]\n" +
                "                }\n" +
                "              ]\n" +
                "            }\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"accountNumber\": \"023170163600014402002\",\n" +
                "          \"brandNameList\": [\n" +
                "            {\n" +
                "              \"brandName\": \"DELTAMED KHAITAN PH\",\n" +
                "              \"locationNameList\": [\n" +
                "                {\n" +
                "                  \"locationName\": \"HATEEN\",\n" +
                "                  \"merchantNumber\": [\n" +
                "                    {\n" +
                "                      \"mid\": \"3549020778\",\n" +
                "                      \"outletNumber\": [\n" +
                "                        \"127641738\"\n" +
                "                      ]\n" +
                "                    },\n" +
                "                    {\n" +
                "                      \"mid\": \"3549020789\",\n" +
                "                      \"outletNumber\": [\n" +
                "                        \"127641749\"\n" +
                "                      ]\n" +
                "                    }\n" +
                "                  ]\n" +
                "                }\n" +
                "              ]\n" +
                "            },\n" +
                "            {\n" +
                "              \"brandName\": \"TAD KHAITAN PH\",\n" +
                "              \"locationNameList\": [\n" +
                "                {\n" +
                "                  \"locationName\": \"MIDAN HAWALLY\",\n" +
                "                  \"merchantNumber\": [\n" +
                "                    {\n" +
                "                      \"mid\": \"3549020761\",\n" +
                "                      \"outletNumber\": [\n" +
                "                        \"127641721\"\n" +
                "                      ]\n" +
                "                    },\n" +
                "                    {\n" +
                "                      \"mid\": \"3549020752\",\n" +
                "                      \"outletNumber\": [\n" +
                "                        \"127641712\"\n" +
                "                      ]\n" +
                "                    }\n" +
                "                  ]\n" +
                "                }\n" +
                "              ]\n" +
                "            },\n" +
                "            {\n" +
                "              \"brandName\": \"SHOHADA PH\",\n" +
                "              \"locationNameList\": [\n" +
                "                {\n" +
                "                  \"locationName\": \"ALSALAM\",\n" +
                "                  \"merchantNumber\": [\n" +
                "                    {\n" +
                "                      \"mid\": \"3549020743\",\n" +
                "                      \"outletNumber\": [\n" +
                "                        \"127641703\"\n" +
                "                      ]\n" +
                "                    }\n" +
                "                  ]\n" +
                "                }\n" +
                "              ]\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}"
        mFilterres = Gson().fromJson(res, FilterRes::class.java)
        mViewModel = ViewModelProviders.of(this@SplashScreenActivity).get(HomeScreenViewModel::class.java)
        mViewModel?.filterResLivedata?.value = mFilterres
        mViewModel?.mSelectedAccCount?.value = 0
        mViewModel?.mSelectedBrandCount?.value = 0
        mViewModel?.mSelectedLocationCount?.value = 0
        btn_filter?.setOnClickListener {
            val addPhotoBottomDialogFragment: ApplyFilterBottomDialogFragment =
                ApplyFilterBottomDialogFragment.newInstance()
            addPhotoBottomDialogFragment.show(
                supportFragmentManager,
                "AddPhotoBottomDialogFragment"
            )
        }

        mViewModel?.filterResLivedata?.observe(this, Observer<FilterRes> {
            mFilterres = it
        })
    }


}