package moe.chenxy.extrasettings.dolby;

import androidx.core.app.NotificationCompat;
import java.lang.reflect.Field;

import moe.chenxy.extrasettings.R;

public class Res {
    private static final String CLASS_NAME_ARRAY = "com.android.internal.R$array";
    private static final String CLASS_NAME_ATTR = "com.android.internal.R$attr";
    private static final String CLASS_NAME_BOOL = "com.android.internal.R$bool";
    private static final String CLASS_NAME_COLOR = "com.android.internal.R$color";
    private static final String CLASS_NAME_DIME = "com.android.internal.R$dime";
    private static final String CLASS_NAME_DRAWABLE = "com.android.internal.R$drawable";
    private static final String CLASS_NAME_ID = "com.android.internal.R$id";
    private static final String CLASS_NAME_INTEGER = "com.android.internal.R$integer";
    private static final String CLASS_NAME_LAYOUT = "com.android.internal.R$layout";
    private static final String CLASS_NAME_STRING = "com.android.internal.R$string";
    private static final String CLASS_NAME_STYLE = "com.android.internal.R$style";
    private static final String CLASS_NAME_STYLEABLE = "com.android.internal.R$styleable";

    public static class array {
        public static int broker_mnc_mcc = Res.getArrayResourceId("broker_mnc_mcc");
        public static int carrier_properties = Res.getArrayResourceId("carrier_properties");
        public static int config_availableColorModes = Res.getArrayResourceId("config_availableColorModes");
        public static int config_disabledUntilUsedPreinstalledCarrierApps = Res.getArrayResourceId("config_disabledUntilUsedPreinstalledCarrierApps");
        public static int config_hideWhenDisabled_packageNames = Res.getArrayResourceId("config_hideWhenDisabled_packageNames");
        public static int config_mobile_hotspot_provision_app = Res.getArrayResourceId("config_mobile_hotspot_provision_app");
        public static int config_nonBlockableNotificationPackages = Res.getArrayResourceId("config_nonBlockableNotificationPackages");
        public static int sim_colors = Res.getArrayResourceId("sim_colors");
        public static int special_locale_codes = Res.getArrayResourceId("special_locale_codes");
        public static int special_locale_names = Res.getArrayResourceId("special_locale_names");
        public static int supported_locales = Res.getArrayResourceId("supported_locales");
    }

    public static class attr {
        public static int checkBoxPreferenceStyle = Res.getAttrResourceId("checkBoxPreferenceStyle");
        public static int expandableListViewStyle = Res.getAttrResourceId("expandableListViewStyle");
        public static int layout_height = Res.getAttrResourceId("layout_height");
        public static int listDivider = Res.getAttrResourceId("listDivider");
        public static int listPreferenceStyle = Res.getAttrResourceId("listPreferenceStyle");
        public static int lockPatternStyle = Res.getArrayResourceId("lockPatternStyle");
        public static int preferenceCategoryStyle = Res.getAttrResourceId("preferenceCategoryStyle");
        public static int preferenceStyle = Res.getAttrResourceId("preferenceStyle");
        public static int radioButtonStyle = Res.getAttrResourceId("radioButtonStyle");
        public static int seekBarPreferenceStyle = Res.getAttrResourceId("seekBarPreferenceStyle");
        public static int seekBarStyle = Res.getAttrResourceId("seekBarStyle");
        public static int state_expanded = Res.getAttrResourceId("state_expanded");
        public static int switchPreferenceStyle = Res.getAttrResourceId("switchPreferenceStyle");
    }

    public static class bool {
        public static int config_allowAllRotations = Res.getBooleanResourceId("config_allowAllRotations");
        public static int config_automatic_brightness_available = Res.getBooleanResourceId("config_automatic_brightness_available");
        public static int config_cameraDoubleTapPowerGestureEnabled = Res.getBooleanResourceId("config_cameraDoubleTapPowerGestureEnabled");
        public static int config_cbrs_supported = Res.getBooleanResourceId("config_cbrs_supported");
        public static int config_cellBroadcastAppLinks = Res.getBooleanResourceId("config_cellBroadcastAppLinks");
        public static int config_debugEnableAutomaticSystemServerHeapDumps = Res.getBooleanResourceId("config_debugEnableAutomaticSystemServerHeapDumps");
        public static int config_device_volte_available = Res.getBooleanResourceId("config_device_volte_available");
        public static int config_dolby_enabled = Res.getBooleanResourceId("config_dolby_enabled");
        public static int config_dreamsActivatedOnDockByDefault = Res.getBooleanResourceId("config_dreamsActivatedOnDockByDefault");
        public static int config_dreamsActivatedOnSleepByDefault = Res.getBooleanResourceId("config_dreamsActivatedOnSleepByDefault");
        public static int config_dreamsEnabledByDefault = Res.getBooleanResourceId("config_dreamsEnabledByDefault");
        public static int config_dreamsSupported = Res.getBooleanResourceId("config_dreamsSupported");
        public static int config_eap_sim_based_auth_supported = Res.getBooleanResourceId("config_eap_sim_based_auth_supported");
        public static int config_enableNewAutoSelectNetworkUI = Res.getBooleanResourceId("config_enableNewAutoSelectNetworkUI");
        public static int config_enableWallpaperService = Res.getBooleanResourceId("config_enableWallpaperService");
        public static int config_enabled_notification_volume = Res.getBooleanResourceId("config_enabled_notification_volume");
        public static int config_fullFunctionHotspot = Res.getBooleanResourceId("config_fullFunctionHotspot");
        public static int config_inflateSignalStrength = Res.getBooleanResourceId("config_inflateSignalStrength");
        public static int config_intrusiveNotificationLed = Res.getBooleanResourceId("config_intrusiveNotificationLed");
        public static int config_notificationBadging = Res.getBooleanResourceId("config_notificationBadging");
        public static int config_screen_has_notch = Res.getBooleanResourceId("config_screen_has_notch");
        public static int config_sendPackageName = Res.getBooleanResourceId("config_sendPackageName");
        public static int config_setColorTransformAccelerated = Res.getBooleanResourceId("config_setColorTransformAccelerated");
        public static int config_showAreaUpdateInfoSettings = Res.getBooleanResourceId("config_showAreaUpdateInfoSettings");
        public static int config_showNavigationBar = Res.getBooleanResourceId("config_showNavigationBar");
        public static int config_smart_battery_available = Res.getBooleanResourceId("config_smart_battery_available");
        public static int config_supportDoubleTapWake = Res.getBooleanResourceId("config_supportDoubleTapWake");
        public static int config_supportSystemNavigationKeys = Res.getBooleanResourceId("config_supportSystemNavigationKeys");
        public static int config_swipe_up_gesture_default = Res.getBooleanResourceId("config_swipe_up_gesture_default");
        public static int config_swipe_up_gesture_setting_available = Res.getBooleanResourceId("config_swipe_up_gesture_setting_available");
        public static int config_voice_capable = Res.getBooleanResourceId("config_voice_capable");
        public static int config_volumeHushGestureEnabled = Res.getBooleanResourceId("config_volumeHushGestureEnabled");
        public static int config_wifi_connected_mac_randomization_supported = Res.getBooleanResourceId("config_wifi_connected_mac_randomization_supported");
        public static int config_wifi_dual_sap_mode_enabled = Res.getBooleanResourceId("config_wifi_dual_sap_mode_enabled");
        public static int enable_pbap_pce_profile = Res.getBooleanResourceId("enable_pbap_pce_profile");
        public static int feature_4397_vzw_unsecure_wifi_connectivity_warning = Res.getBooleanResourceId("feature_4397_vzw_unsecure_wifi_connectivity_warning");
        public static int ftr_5204_dualsim_ringtone_settings = Res.getBooleanResourceId("ftr_5204_dualsim_ringtone_settings");
    }

    public static class color {
        public static int instant_app_badge = Res.getColorResourceId("instant_app_badge");
        public static int profile_badge_1 = Res.getColorResourceId("profile_badge_1");
        public static int profile_badge_2 = Res.getColorResourceId("profile_badge_2");
        public static int profile_badge_3 = Res.getColorResourceId("profile_badge_3");
        public static int system_notification_accent_color = Res.getColorResourceId("system_notification_accent_color");
        public static int transparent = Res.getColorResourceId("transparent");
        public static int white = Res.getColorResourceId("white");
    }

    public static class dimen {
        public static int preference_fragment_padding_bottom = Res.getDimeResourceId("preference_fragment_padding_bottom");
        public static int status_bar_height = Res.getDimeResourceId("status_bar_height");
        public static int toast_y_offset = Res.getDimeResourceId("toast_y_offset");
    }

    public static class drawable {
        public static int expander_ic_maximized = Res.getDrawableResourceId("expander_ic_maximized");
        public static int expander_ic_minimized = Res.getDrawableResourceId("expander_ic_minimized");
        public static int ic_audio_alarm_mute = Res.getDrawableResourceId("ic_audio_alarm_mute");
        public static int ic_audio_media_mute = Res.getDrawableResourceId("ic_audio_media_mute");
        public static int ic_audio_ring_notif_mute = Res.getDrawableResourceId("ic_audio_ring_notif_mute");
        public static int ic_bt_headphones_a2dp = Res.getDrawableResourceId("ic_bt_headphones_a2dp");
        public static int ic_bt_headset_hfp = Res.getDrawableResourceId("ic_bt_headset_hfp");
        public static int ic_bt_hearing_aid = Res.getDrawableResourceId("ic_bt_hearing_aid");
        public static int ic_bt_laptop = Res.getDrawableResourceId("ic_bt_laptop");
        public static int ic_bt_misc_hid = Res.getDrawableResourceId("ic_bt_misc_hid");
        public static int ic_bt_network_pan = Res.getDrawableResourceId("ic_bt_network_pan");
        public static int ic_bt_pointing_hid = Res.getDrawableResourceId("ic_bt_pointing_hid");
        public static int ic_corp_badge = Res.getDrawableResourceId("ic_corp_badge");
        public static int ic_corp_badge_case = Res.getDrawableResourceId("ic_corp_badge_case");
        public static int ic_corp_icon = Res.getDrawableResourceId("ic_corp_icon");
        public static int ic_corp_icon_badge_case = Res.getDrawableResourceId("ic_corp_icon_badge_case");
        public static int ic_corp_user_badge = Res.getDrawableResourceId("ic_corp_user_badge");
        public static int ic_dialog_alert_material = Res.getDrawableResourceId("ic_dialog_alert_material");
        public static int ic_info = Res.getDrawableResourceId("ic_info");
        public static int ic_instant_icon_badge_bolt = Res.getDrawableResourceId("ic_instant_icon_badge_bolt");
        public static int ic_lockscreen_ime = Res.getDrawableResourceId("ic_lockscreen_ime");
        public static int ic_menu_cc = Res.getDrawableResourceId("ic_menu_cc");
        public static int ic_menu_close_clear_cancel = Res.getDrawableResourceId("ic_menu_close_clear_cancel");
        public static int ic_menu_info_details = Res.getDrawableResourceId("ic_menu_info_details");
        public static int ic_mode_edit = Res.getDrawableResourceId("ic_mode_edit");
        public static int ic_phone = Res.getDrawableResourceId("ic_phone");
        public static int ic_print = Res.getDrawableResourceId("ic_print");
        public static int ic_print_error = Res.getDrawableResourceId("ic_print_error");
        public static int ic_sd_card_48dp = Res.getDrawableResourceId("ic_sd_card_48dp");
        public static int ic_settings_bluetooth = Res.getDrawableResourceId("ic_settings_bluetooth");
        public static int ic_settings_print = Res.getDrawableResourceId("ic_settings_print");
        public static int ic_signal_cellular = Res.getDrawableResourceId("ic_signal_cellular");
        public static int ic_signal_location = Res.getDrawableResourceId("ic_signal_location");
        public static int ic_signal_wifi_badged_0_bars = Res.getDrawableResourceId("ic_signal_wifi_badged_0_bars");
        public static int ic_signal_wifi_badged_1_bar = Res.getDrawableResourceId("ic_signal_wifi_badged_1_bar");
        public static int ic_signal_wifi_badged_2_bars = Res.getDrawableResourceId("ic_signal_wifi_badged_2_bars");
        public static int ic_signal_wifi_badged_3_bars = Res.getDrawableResourceId("ic_signal_wifi_badged_3_bars");
        public static int ic_signal_wifi_badged_4_bars = Res.getDrawableResourceId("ic_signal_wifi_badged_4_bars");
        public static int ic_signal_wifi_badged_4k = Res.getDrawableResourceId("ic_signal_wifi_badged_4k");
        public static int ic_signal_wifi_badged_hd = Res.getDrawableResourceId("ic_signal_wifi_badged_hd");
        public static int ic_signal_wifi_badged_sd = Res.getDrawableResourceId("ic_signal_wifi_badged_sd");
        public static int ic_text_dot = Res.getDrawableResourceId("ic_text_dot");
        public static int ic_wifi_4_signal_0 = Res.getDrawableResourceId("ic_wifi_4_signal_0");
        public static int ic_wifi_4_signal_1 = Res.getDrawableResourceId("ic_wifi_4_signal_1");
        public static int ic_wifi_4_signal_2 = Res.getDrawableResourceId("ic_wifi_4_signal_2");
        public static int ic_wifi_4_signal_3 = Res.getDrawableResourceId("ic_wifi_4_signal_3");
        public static int ic_wifi_4_signal_4 = Res.getDrawableResourceId("ic_wifi_4_signal_4");
        public static int ic_wifi_5_signal_0 = Res.getDrawableResourceId("ic_wifi_5_signal_0");
        public static int ic_wifi_5_signal_1 = Res.getDrawableResourceId("ic_wifi_5_signal_1");
        public static int ic_wifi_5_signal_2 = Res.getDrawableResourceId("ic_wifi_5_signal_2");
        public static int ic_wifi_5_signal_3 = Res.getDrawableResourceId("ic_wifi_5_signal_3");
        public static int ic_wifi_5_signal_4 = Res.getDrawableResourceId("ic_wifi_5_signal_4");
        public static int ic_wifi_6_signal_0 = Res.getDrawableResourceId("ic_wifi_6_signal_0");
        public static int ic_wifi_6_signal_1 = Res.getDrawableResourceId("ic_wifi_6_signal_1");
        public static int ic_wifi_6_signal_2 = Res.getDrawableResourceId("ic_wifi_6_signal_2");
        public static int ic_wifi_6_signal_3 = Res.getDrawableResourceId("ic_wifi_6_signal_3");
        public static int ic_wifi_6_signal_4 = Res.getDrawableResourceId("ic_wifi_6_signal_4");
        public static int ic_wifi_signal_0 = Res.getDrawableResourceId("ic_wifi_signal_0");
        public static int ic_wifi_signal_1 = Res.getDrawableResourceId("ic_wifi_signal_1");
        public static int ic_wifi_signal_2 = Res.getDrawableResourceId("ic_wifi_signal_2");
        public static int ic_wifi_signal_3 = Res.getDrawableResourceId("ic_wifi_signal_3");
        public static int ic_wifi_signal_4 = Res.getDrawableResourceId("ic_wifi_signal_4");
        public static int lockscreen_notselected = Res.getDrawableResourceId("lockscreen_notselected");
        public static int lockscreen_selected = Res.getDrawableResourceId("lockscreen_selected");
        public static int sym_app_on_sd_unavailable_icon = Res.getDrawableResourceId("sym_app_on_sd_unavailable_icon");
    }

    public static class id {
        public static int alwaysUse = Res.getIdResourceId("alwaysUse");
        public static int button1 = Res.getIdResourceId("button1");
        public static int button2 = Res.getIdResourceId("button2");
        public static int checkbox = Res.getIdResourceId("checkbox");
        public static int customPanel = Res.getIdResourceId("customPanel");
        public static int edit = Res.getIdResourceId("edit");
        public static int empty = Res.getIdResourceId("empty");
        public static int home = Res.getIdResourceId("home");
        public static int icon = Res.getIdResourceId("icon");
        public static int list_container = Res.getIdResourceId("list_container");
        public static int message = Res.getIdResourceId("message");
        public static int password_scanner_button = Res.getIdResourceId("password_scanner_button");
        public static int perm_icon = Res.getIdResourceId("perm_icon");
        public static int permission_group = Res.getIdResourceId("permission_group");
        public static int permission_list = Res.getIdResourceId("permission_list");
        public static int progress = Res.getIdResourceId(NotificationCompat.CATEGORY_PROGRESS);
        public static int radio = Res.getIdResourceId("radio");
        public static int search_edit_frame = Res.getIdResourceId("search_edit_frame");
        public static int search_src_text = Res.getIdResourceId("search_src_text");
        public static int seekbar = Res.getIdResourceId("seekbar");
        public static int ssid_scanner_button = Res.getIdResourceId("ssid_scanner_button");
        public static int summary = Res.getIdResourceId("summary");
        public static int switch_widget = Res.getIdResourceId("switch_widget");
        public static int title = Res.getIdResourceId("title");
        public static int widget_frame = Res.getIdResourceId("widget_frame");
    }

    public static class integer {
        public static int config_bluetooth_max_connected_audio_devices = Res.getIntegerResourceId("config_bluetooth_max_connected_audio_devices");
        public static int config_cameraLaunchGestureSensorType = Res.getIntegerResourceId("config_cameraLaunchGestureSensorType");
        public static int config_criticalBatteryWarningLevel = Res.getIntegerResourceId("config_criticalBatteryWarningLevel");
        public static int config_faceMaxTemplatesPerUser = Res.getIntegerResourceId("config_faceMaxTemplatesPerUser");
        public static int config_fingerprintMaxTemplatesPerUser = Res.getIntegerResourceId("config_fingerprintMaxTemplatesPerUser");
        public static int config_lowBatteryWarningLevel = Res.getIntegerResourceId("config_lowBatteryWarningLevel");
        public static int config_mobile_hotspot_provision_check_period = Res.getIntegerResourceId("config_mobile_hotspot_provision_check_period");
        public static int config_navBarInteractionMode = Res.getIntegerResourceId("config_navBarInteractionMode");
        public static int config_networkAvoidBadWifi = Res.getIntegerResourceId("config_networkAvoidBadWifi");
        public static int config_toastDefaultGravity = Res.getIntegerResourceId("config_toastDefaultGravity");
        public static int config_wifi_wakeup_available = Res.getIntegerResourceId("config_wifi_wakeup_available");
        public static int config_zen_repeat_callers_threshold = Res.getIntegerResourceId("config_zen_repeat_callers_threshold");
        public static int default_data_warning_level_mb = Res.getIntegerResourceId("default_data_warning_level_mb");
    }

    public static class layout {
        public static int always_use_checkbox = Res.getLayoutResourceId("always_use_checkbox");
        public static int app_permission_item_old = Res.getLayoutResourceId("app_permission_item_old");
        public static int data_usage_bytes_editor = Res.getLayoutResourceId("data_usage_bytes_editor");
        public static int preference_list_fragment = Res.getLayoutResourceId("preference_list_fragment");
        public static int simple_list_item_2_single_choice = Res.getLayoutResourceId("simple_list_item_2_single_choice");
        public static int simple_list_item_single_choice = Res.getLayoutResourceId("simple_list_item_single_choice");
        public static int simple_spinner_dropdown_item = Res.getLayoutResourceId("simple_spinner_dropdown_item");
        public static int simple_spinner_item = Res.getLayoutResourceId("simple_spinner_item");
        public static int transient_notification = Res.getLayoutResourceId("transient_notification");
    }

    public static class string {
        public static int battery_saver_description_with_learn_more = Res.getStringResourceId("battery_saver_description_with_learn_more");
        public static int biometric_error_canceled = Res.getStringResourceId("biometric_error_canceled");
        public static int biometric_error_user_canceled = Res.getStringResourceId("biometric_error_user_canceled");
        public static int bugreport_option_full_summary = Res.getStringResourceId("bugreport_option_full_summary");
        public static int bugreport_option_full_title = Res.getStringResourceId("bugreport_option_full_title");
        public static int bugreport_option_interactive_summary = Res.getStringResourceId("bugreport_option_interactive_summary");
        public static int bugreport_option_interactive_title = Res.getStringResourceId("bugreport_option_interactive_title");
        public static int bugreport_title = Res.getStringResourceId("bugreport_title");
        public static int cancel = Res.getStringResourceId("cancel");
        public static int config_batterySaverScheduleProvider = Res.getStringResourceId("config_batterySaverScheduleProvider");
        public static int config_carrierDemoModePassword = Res.getStringResourceId("config_carrierDemoModePassword");
        public static int config_carrierDemoModeSetting = Res.getStringResourceId("config_carrierDemoModeSetting");
        public static int config_defaultAccessibilityService = Res.getStringResourceId("config_defaultAccessibilityService");
        public static int config_defaultModuleMetadataProvider = Res.getStringResourceId("config_defaultModuleMetadataProvider");
        public static int config_defaultSupervisionProfileOwnerComponent = Res.getStringResourceId("config_defaultSupervisionProfileOwnerComponent");
        public static int config_deviceProvisioningPackage = Res.getStringResourceId("config_deviceProvisioningPackage");
        public static int config_feedbackIntentExtraKey = Res.getStringResourceId("config_helpIntentNameKey");
        public static int config_feedbackIntentNameKey = Res.getStringResourceId("config_feedbackIntentNameKey");
        public static int config_headlineFontFamily = Res.getStringResourceId("config_headlineFontFamily");
        public static int config_helpIntentExtraKey = Res.getStringResourceId("config_helpIntentExtraKey");
        public static int config_helpIntentNameKey = Res.getStringResourceId("config_helpIntentNameKey");
        public static int config_helpPackageNameKey = Res.getStringResourceId("config_helpPackageNameKey");
        public static int config_helpPackageNameValue = Res.getStringResourceId("config_helpPackageNameValue");
        public static int config_icon_mask = Res.getStringResourceId("config_icon_mask");
        public static int config_mainBuiltInDisplayCutout = Res.getStringResourceId("config_mainBuiltInDisplayCutout");
        public static int config_mobile_hotspot_provision_app_no_ui = Res.getStringResourceId("config_mobile_hotspot_provision_app_no_ui");
        public static int config_mobile_hotspot_provision_response = Res.getStringResourceId("config_mobile_hotspot_provision_response");
        public static int config_packagedKeyboardName = Res.getStringResourceId("config_packagedKeyboardName");
        public static int config_recentsComponentName = Res.getStringResourceId("config_recentsComponentName");
        public static int data_saver_description = Res.getStringResourceId("data_saver_description");
        public static int dialog_alert_title = Res.getStringResourceId("dialog_alert_title");
        public static int done_label = Res.getStringResourceId("done_label");
        public static int ext_media_status_missing = Res.getStringResourceId("ext_media_status_missing");
        public static int ext_media_status_unsupported = Res.getStringResourceId("ext_media_status_unsupported");
        public static int fast_scroll_alphabet = Res.getStringResourceId("fast_scroll_alphabet");
        public static int fileSizeSuffix = Res.getStringResourceId("fileSizeSuffix");
        public static int gigabyteShort = Res.getStringResourceId("gigabyteShort");
        public static int lockscreen_access_pattern_area = Res.getStringResourceId("lockscreen_access_pattern_area");
        public static int lockscreen_access_pattern_cell_added_verbose = Res.getStringResourceId("lockscreen_access_pattern_cell_added_verbose");
        public static int lockscreen_access_pattern_cleared = Res.getStringResourceId("lockscreen_access_pattern_cleared");
        public static int lockscreen_access_pattern_detected = Res.getStringResourceId("lockscreen_access_pattern_detected");
        public static int lockscreen_access_pattern_start = Res.getStringResourceId("lockscreen_access_pattern_start");
        public static int megabyteShort = Res.getStringResourceId("megabyteShort");
        public static int muted_by = Res.getStringResourceId("muted_by");
        public static int no = Res.getStringResourceId("no");
        public static int noApplications = Res.getStringResourceId("noApplications");
        public static int ok = Res.getStringResourceId("ok");
        public static int report = Res.getStringResourceId("report");
        public static int ringtone_silent = Res.getStringResourceId("ringtone_silent");
        public static int ringtone_unknown = Res.getStringResourceId("ringtone_unknown");
        public static int ssl_certificate = Res.getStringResourceId("ssl_certificate");
        public static int storage_sd_card = Res.getStringResourceId("storage_sd_card");
        public static int storage_usb_drive = Res.getStringResourceId("storage_usb_drive");
        public static int unknownName = Res.getStringResourceId("unknownName");
        public static int wfc_mode_cellular_preferred_summary = Res.getStringResourceId("wfc_mode_cellular_preferred_summary");
        public static int wfc_mode_wifi_only_summary = Res.getStringResourceId("wfc_mode_wifi_only_summary");
        public static int wfc_mode_wifi_preferred_summary = Res.getStringResourceId("wfc_mode_wifi_preferred_summary");
        public static int wifi_calling_off_summary = Res.getStringResourceId("wifi_calling_off_summary");
        public static int wifi_tether_configure_ssid_default = Res.getStringResourceId("wifi_tether_configure_ssid_default");
        public static int yes = Res.getStringResourceId("yes");
        public static int zen_mode_forever = Res.getStringResourceId("zen_mode_forever");
        public static int zen_mode_forever_dnd = Res.getStringResourceId("zen_mode_forever_dnd");
    }

    public static class style {
        public static int Animation_Toast = Res.getStyleResourceId("Animation_Toast");
        public static int Widget_LockPatternView = Res.getStyleResourceId("Widget_LockPatternView");
    }

    public static class styleable {
        public static int[] CheckBoxPreference = Res.getStyleArrayResourceId("CheckBoxPreference");
        public static int CheckBoxPreference_disableDependentsState = Res.getStyleableResourceId("CheckBoxPreference_disableDependentsState");
        public static int CheckBoxPreference_summaryOff = Res.getStyleableResourceId("CheckBoxPreference_summaryOff");
        public static int CheckBoxPreference_summaryOn = Res.getStyleableResourceId("CheckBoxPreference_summaryOn");
        public static int[] Dream = Res.getStyleArrayResourceId("Dream");
        public static int Dream_settingsActivity = Res.getStyleableResourceId("Dream_settingsActivity");
        public static int[] ExpandableListView = Res.getStyleArrayResourceId("ExpandableListView");
        public static int ExpandableListView_groupIndicator = Res.getStyleableResourceId("ExpandableListView_groupIndicator");
        public static int Icon_icon = Res.getStyleableResourceId("Icon_icon");
        public static int[] LinearLayout = Res.getStyleArrayResourceId("LinearLayout");
        public static int[] LinearLayout_Layout = Res.getStyleArrayResourceId("LinearLayout_Layout");
        public static int LinearLayout_Layout_layout_gravity = Res.getStyleableResourceId("LinearLayout_Layout_layout_gravity");
        public static int LinearLayout_Layout_layout_weight = Res.getStyleableResourceId("LinearLayout_Layout_layout_weight");
        public static int LinearLayout_baselineAligned = Res.getStyleableResourceId("LinearLayout_baselineAligned");
        public static int LinearLayout_baselineAlignedChildIndex = Res.getStyleableResourceId("LinearLayout_baselineAlignedChildIndex");
        public static int LinearLayout_divider = Res.getStyleableResourceId("LinearLayout_divider");
        public static int LinearLayout_dividerPadding = Res.getStyleableResourceId("LinearLayout_dividerPadding");
        public static int LinearLayout_gravity = Res.getStyleableResourceId("LinearLayout_gravity");
        public static int LinearLayout_measureWithLargestChild = Res.getStyleableResourceId("LinearLayout_measureWithLargestChild");
        public static int LinearLayout_orientation = Res.getStyleableResourceId("LinearLayout_orientation");
        public static int LinearLayout_showDividers = Res.getStyleableResourceId("LinearLayout_showDividers");
        public static int LinearLayout_weightSum = Res.getStyleableResourceId("LinearLayout_weightSum");
        public static int[] ListPreference = Res.getStyleArrayResourceId("ListPreference");
        public static int ListPreference_entries = Res.getStyleableResourceId("ListPreference_entries");
        public static int[] LockPatternView = Res.getStyleArrayResourceId("LockPatternView");
        public static int LockPatternView_aspect = Res.getStyleableResourceId("LockPatternView_aspect");
        public static int LockPatternView_pathColor = Res.getStyleableResourceId("LockPatternView_pathColor");
        public static int[] Preference = Res.getStyleArrayResourceId("Preference");
        public static int Preference_key = Res.getStyleableResourceId("Preference_key");
        public static int Preference_layout = Res.getStyleableResourceId("Preference_layout");
        public static int Preference_summary = Res.getStyleableResourceId("Preference_summary");
        public static int Preference_title = Res.getStyleableResourceId("Preference_title");
        public static int[] ProgressBar = Res.getStyleArrayResourceId("ProgressBar");
        public static int ProgressBar_max = Res.getStyleableResourceId("ProgressBar_max");
        public static int[] RecognitionService = Res.getStyleArrayResourceId("RecognitionService");
        public static int RecognitionService_settingsActivity = Res.getStyleableResourceId("RecognitionService_settingsActivity");
        public static int[] RingtonePreference = Res.getStyleArrayResourceId("RingtonePreference");
        public static int RingtonePreference_ringtoneType = Res.getStyleableResourceId("RingtonePreference_ringtoneType");
        public static int RingtonePreference_showDefault = Res.getStyleableResourceId("RingtonePreference_showDefault");
        public static int RingtonePreference_showSilent = Res.getStyleableResourceId("RingtonePreference_showSilent");
        public static int[] SeekBarPreference = Res.getStyleArrayResourceId("SeekBarPreference");
        public static int[] TextAppearance = Res.getStyleArrayResourceId("TextAppearance");
        public static int TextAppearance_textColor = Res.getStyleableResourceId("TextAppearance_textColor");
        public static int TextAppearance_textSize = Res.getStyleableResourceId("TextAppearance_textSize");
        public static int TextAppearance_textStyle = Res.getStyleableResourceId("TextAppearance_textStyle");
        public static int TextAppearance_typeface = Res.getStyleableResourceId("TextAppearance_typeface");
        public static int[] TrustAgent = Res.getStyleArrayResourceId("TrustAgent");
        public static int TrustAgent_settingsActivity = Res.getStyleableResourceId("TrustAgent_settingsActivity");
        public static int TrustAgent_summary = Res.getStyleableResourceId("TrustAgent_summary");
        public static int TrustAgent_title = Res.getStyleableResourceId("TrustAgent_title");
    }

    /* access modifiers changed from: private */
    public static int getStringResourceId(String str) {
        try {
            Field field = Class.forName(CLASS_NAME_STRING).getField(str);
            field.setAccessible(true);
            return field.getInt(null);
        } catch (Exception unused) {
            return R.string.default_value;
        }
    }

    /* access modifiers changed from: private */
    public static int getBooleanResourceId(String str) {
        try {
            Field field = Class.forName(CLASS_NAME_BOOL).getField(str);
            field.setAccessible(true);
            return field.getInt(null);
        } catch (Exception unused) {
            return R.bool.default_value;
        }
    }

    /* access modifiers changed from: private */
    public static int getDrawableResourceId(String str) {
        try {
            Field field = Class.forName(CLASS_NAME_DRAWABLE).getField(str);
            field.setAccessible(true);
            return field.getInt(null);
        } catch (Exception unused) {
            return R.drawable.default_value;
        }
    }

    /* access modifiers changed from: private */
    public static int getColorResourceId(String str) {
        try {
            Field field = Class.forName(CLASS_NAME_COLOR).getField(str);
            field.setAccessible(true);
            return field.getInt(null);
        } catch (Exception unused) {
            return R.color.default_value;
        }
    }

    /* access modifiers changed from: private */
    public static int getIntegerResourceId(String str) {
        try {
            Field field = Class.forName(CLASS_NAME_INTEGER).getField(str);
            field.setAccessible(true);
            return field.getInt(null);
        } catch (Exception unused) {
            return R.integer.default_value;
        }
    }

    /* access modifiers changed from: private */
    public static int getAttrResourceId(String str) {
        try {
            Field field = Class.forName(CLASS_NAME_ATTR).getField(str);
            field.setAccessible(true);
            return field.getInt(null);
        } catch (Exception unused) {
            return R.attr.default_value;
        }
    }

    /* access modifiers changed from: private */
    public static int getArrayResourceId(String str) {
        try {
            Field field = Class.forName(CLASS_NAME_ARRAY).getField(str);
            field.setAccessible(true);
            return field.getInt(null);
        } catch (Exception unused) {
            return R.array.default_value;
        }
    }

    /* access modifiers changed from: private */
    public static int getStyleableResourceId(String str) {
        try {
            Field field = Class.forName(CLASS_NAME_STYLEABLE).getField(str);
            field.setAccessible(true);
            return field.getInt(null);
        } catch (Exception unused) {
            return R.style.default_value;
        }
    }

    /* access modifiers changed from: private */
    public static int[] getStyleArrayResourceId(String str) {
        try {
            Field field = Class.forName(CLASS_NAME_STYLEABLE).getField(str);
            field.setAccessible(true);
            return (int[]) field.get(null);
        } catch (Exception unused) {
            return new int[0];
        }
    }

    /* access modifiers changed from: private */
    public static int getLayoutResourceId(String str) {
        try {
            Field field = Class.forName(CLASS_NAME_LAYOUT).getField(str);
            field.setAccessible(true);
            return ((Integer) field.get(null)).intValue();
        } catch (Exception unused) {
            return R.layout.default_value;
        }
    }

    /* access modifiers changed from: private */
    public static int getIdResourceId(String str) {
        try {
            Field field = Class.forName(CLASS_NAME_ID).getField(str);
            field.setAccessible(true);
            return ((Integer) field.get(null)).intValue();
        } catch (Exception unused) {
            return R.id.default_value;
        }
    }

    /* access modifiers changed from: private */
    public static int getStyleResourceId(String str) {
        try {
            Field field = Class.forName(CLASS_NAME_STYLE).getField(str);
            field.setAccessible(true);
            return ((Integer) field.get(null)).intValue();
        } catch (Exception unused) {
            return R.style.default_value;
        }
    }

    /* access modifiers changed from: private */
    public static int getDimeResourceId(String str) {
        try {
            Field field = Class.forName(CLASS_NAME_DIME).getField(str);
            field.setAccessible(true);
            return ((Integer) field.get(null)).intValue();
        } catch (Exception unused) {
            return R.dimen.default_value;
        }
    }
}