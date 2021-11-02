package moe.chenxy.extrasettings.dolby;

import androidx.preference.Preference;
import androidx.preference.TwoStatePreference;
import java.util.ArrayList;
import java.util.List;

public class RadioPreferenceGroup {
    private int mCheckId = -1;
    private List<TwoStatePreference> mPreferenceList = new ArrayList();

    public List<TwoStatePreference> getPreferenceList() {
        return this.mPreferenceList;
    }

    public void check(int i) {
        int size = this.mPreferenceList.size();
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("group count =" + size + ", index =" + i);
        }
        int i2 = 0;
        while (i2 < size) {
            Preference preference = this.mPreferenceList.get(i2);
            if (preference instanceof TwoStatePreference) {
                ((TwoStatePreference) preference).setChecked(i2 == i);
            }
            i2++;
        }
        this.mCheckId = i;
    }

    public void check(TwoStatePreference twoStatePreference) {
        check(this.mPreferenceList.indexOf(twoStatePreference));
    }

    public int getCheckId() {
        return this.mCheckId;
    }

    public void addPreference(TwoStatePreference twoStatePreference) {
        if (!this.mPreferenceList.contains(twoStatePreference)) {
            this.mPreferenceList.add(twoStatePreference);
        }
    }

    public void removeAll() {
        this.mPreferenceList.clear();
        this.mCheckId = -1;
    }

    public boolean containsItem(TwoStatePreference twoStatePreference) {
        return this.mPreferenceList.contains(twoStatePreference);
    }

    public int getPreferenceCount() {
        return this.mPreferenceList.size();
    }

    public TwoStatePreference getPreference(int i) {
        return this.mPreferenceList.get(i);
    }
}