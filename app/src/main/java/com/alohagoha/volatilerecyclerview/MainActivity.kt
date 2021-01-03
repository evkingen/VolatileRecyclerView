package com.alohagoha.volatilerecyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

//todo
/*
-Оформить в гите
-сделать ветку и попробовать через дифутилс и через ЛистАдаптер
-поизучать MVI
-поизучать интерактор и репозиторий
-разобраться где хранить бизнес-модель
-сделать задание со звездочкой
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, ListFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        }
    }
}
