package org.sopt.dosopttemplate.presentation.home

import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.presentation.model.MyProfile
import org.sopt.dosopttemplate.presentation.model.OtherProfile

class HomeViewModel : ViewModel() {
    val mockList = mutableListOf(
        MyProfile(
            userId = "lsakee",
            name = "Sak",
            picture = "https://avatars.githubusercontent.com/u/93514333?v=4"
        ),
        OtherProfile(
            userId = "stella",
            name = "Hailey",
            picture = "https://avatars.githubusercontent.com/u/91793891?v=4"
        ),
        OtherProfile(
            userId = "chetty",
            name = "Dongmin Park",
            picture = "https://avatars.githubusercontent.com/u/52882799?v=4"
        ),
        OtherProfile(
            userId = "crownjoe",
            name = "crownjoe",
            picture = "https://avatars.githubusercontent.com/u/135544903?v=4"
        ),
        OtherProfile(
            userId = "jihyun",
            name = "JiHyun Kyoung",
            picture = "https://avatars.githubusercontent.com/u/84266681?v=4"
        )
    )
}