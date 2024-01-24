package dev.goobar.composedemo.ui.tooling

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(group = "pixel7 pro", device = Devices.PIXEL_7_PRO, showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(group = "pixel7 pro", device = Devices.PIXEL_7_PRO, showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(group = "pixel tablet", device = Devices.PIXEL_TABLET, showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(group = "pixel tablet", device = Devices.PIXEL_TABLET, showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
annotation class StandardPreview