package info.nightscout.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mike on 22.12.2017.
 */

public class PercentageSplitter {
    // "Profile name (200%,2h)"
    private static final Pattern percentagePattern = Pattern.compile("(.+)\\(\\d+%,-?\\d+h\\)");
    // "Profile name (200%)"
    private static final Pattern percentageShiftPattern = Pattern.compile("(.+)\\(\\d+%\\)");

    /** Removes the suffix for percentage and timeshift from a profile name. This is the inverse of what
     * {@link info.nightscout.androidaps.db.ProfileSwitch#getCustomizedName()} does.
     * Since the customized name is used for the PS upload to NS, this is needed get the original profile name
     * when retrieving the PS from NS again. */
    public static String pureName(String name) {
        Matcher percentageMatch = percentagePattern.matcher(name);
        if (percentageMatch.find()) {
            return percentageMatch.group(1).trim();
        }

        Matcher percentageShiftMatch = percentageShiftPattern.matcher(name);
        if (percentageShiftMatch.find()) {
            return percentageShiftMatch.group(1).trim();
        }

        return name;
    }
}
