package edu.berkeley.boinc.settings

import android.content.Context
import android.widget.SeekBar
import android.widget.TextView
import androidx.preference.PreferenceViewHolder
import androidx.preference.SeekBarPreference
import edu.berkeley.boinc.R

class IncrementalSeekBarPreference(context: Context) : SeekBarPreference(context), SeekBar.OnSeekBarChangeListener {
    private lateinit var seekBar: SeekBar
    private lateinit var summary: TextView

    init {
        layoutResource = R.layout.incremental_seek_bar_preference
    }

    override fun onBindViewHolder(holder: PreferenceViewHolder) {
        super.onBindViewHolder(holder)
        seekBar = holder.findViewById(R.id.seekbar) as SeekBar
        summary = holder.findViewById(R.id.summary) as TextView
        seekBar.setOnSeekBarChangeListener(this)
    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        // Assuming that the increment between values is 5
        val newProgress = progress * 5;
        summary.text = newProgress.toString()
        persistInt(newProgress)
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {}
    override fun onStopTrackingTouch(seekBar: SeekBar?) {}
}
