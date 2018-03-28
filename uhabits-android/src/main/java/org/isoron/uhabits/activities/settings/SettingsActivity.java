/*
 * Copyright (C) 2016 Álinson Santos Xavier <isoron@gmail.com>
 *
 * This file is part of Loop Habit Tracker.
 *
 * Loop Habit Tracker is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * Loop Habit Tracker is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.isoron.uhabits.activities.settings;

import android.content.SharedPreferences;
import android.os.*;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;

import org.isoron.androidbase.activities.*;
import org.isoron.androidbase.utils.*;
import org.isoron.uhabits.R;
import org.isoron.uhabits.core.utils.DateUtils;

/**
 * Activity that allows the user to view and modify the app settings.
 */
public class SettingsActivity extends BaseActivity implements SharedPreferences.OnSharedPreferenceChangeListener
{
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        setupActionBarColor();
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);
    }

    private void setupActionBarColor()
    {
        StyledResources res = new StyledResources(this);
        int color = BaseScreen.getDefaultActionBarColor(this);

        if (res.getBoolean(R.attr.useHabitColorAsPrimary))
            color = res.getColor(R.attr.aboutScreenColor);

        BaseScreen.setupActionBarColor(this, color);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPref, String key)
    {
        SharedPreferences.Editor editor = sharedPref.edit();
        if (key.equalsIgnoreCase("pref_new_day_offset"))
        {
            int dayHourOffset = 0;
            try
            {
                dayHourOffset = Integer.parseInt(sharedPref.getString(key, "0"));
                DateUtils.setNewDayOffset(dayHourOffset);
            }
            catch (NumberFormatException e)
            {
                e.printStackTrace();
            }

        }
        else if (key.equalsIgnoreCase("pref_first_week_day"))
        {
            int firstWeekDay = 0;
            try
            {
                firstWeekDay = Integer.parseInt(sharedPref.getString(key, "0"));
                DateUtils.setFirstWeekDay(firstWeekDay);
            }
            catch (NumberFormatException e)
            {
                e.printStackTrace();
            }
        }
    }
}
