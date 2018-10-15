package com.example.valentin.chipexample

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.chip.Chip
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.app.AlertDialog


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val chips = resources.getStringArray(R.array.countries).toList()
        addChips(chips)

        button.setOnClickListener {
            addChip(edit_text.text.toString())
            edit_text.text?.clear()
            edit_text.onEditorAction(EditorInfo.IME_ACTION_DONE)
        }

    }

    private fun addChips(chips: List<String>) {
        chips.forEach { addChip(it) }
    }

    private fun addChip(name: String) {
        val chip = Chip(this)
        chip.text = name
        chip.setOnClickListener { showDialog(chip.text.toString()) }
        chip_group.addView(chip)
    }

    private fun showDialog(name: String) {
        val singleChoiceItems = resources.getStringArray(R.array.actions)
        var itemSelected = 0
        AlertDialog.Builder(this)
            .setTitle(resources.getString(R.string.choose_action))
            .setSingleChoiceItems(singleChoiceItems, itemSelected) { _, i -> itemSelected = i }
            .setPositiveButton(resources.getString(R.string.ok))
            { _, _ -> this.toast(singleChoiceItems[itemSelected]) }
            .setNegativeButton(resources.getString(R.string.cancel))
            { _, _ -> this.toast("Cancel: $name") }
            .show()
    }

    fun Context.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT): Toast {
        return Toast.makeText(this, text, duration).apply { show() }
    }
}
