package com.bramwel.intentapp.ui.theme.screens.intent


import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.ContactsContract
import android.provider.MediaStore
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.bramwel.intentapp.data.SecondActivity

data class IntentFeature(
    val icon: ImageVector,
    val title: String,
    val color: Color,
    val onClick: () -> Unit
)

data class IntentCategory(
    val title: String,
    val features: List<IntentFeature>
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntentFeaturesScreen() {
    val context = LocalContext.current
    val categories = listOf(
        // App Navigation
        IntentCategory(
            title = "📱 App Navigation",
            features = listOf(
                IntentFeature(
                    icon = Icons.Default.OpenInNew,
                    title = "Open Activity",
                    color = Color(0xFF4CAF50),
                    onClick = { /* Open another activity */
                        val intent = Intent(context, SecondActivity::class.java)
                        context.startActivity(intent)
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.Send,
                    title = "Pass Data",
                    color = Color(0xFF2196F3),
                    onClick = { /* Pass data between screens */
                        val intent = Intent(context, SecondActivity::class.java).apply {
                            putExtra("data", "This is data sent from MainActivity")
                        }
                        context.startActivity(intent)
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.Reply,
                    title = "Get Results",
                    color = Color(0xFFFF9800),
                    onClick = { /* Get results back */


                    }
                )
            )
        ),
        // Phone & Communication
        IntentCategory(
            title = "📞 Phone & Communication",
            features = listOf(
                IntentFeature(
                    icon = Icons.Default.Dialpad,
                    title = "Dial Number",
                    color = Color(0xFF4CAF50),
                    onClick = { /* ACTION_DIAL */
                        val phone = "+254737467020"

                        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))

                        context.startActivity(intent)


                    }
                ),
                IntentFeature(
                    icon = Icons.Default.Call,
                    title = "Make Call",
                    color = Color(0xFF2196F3),
                    onClick = { /* ACTION_CALL */
                        val intent = Intent(Intent.ACTION_CALL, ("tel:" + "+254723633522").toUri())

                        if (ContextCompat.checkSelfPermission(
                                context,
                                android.Manifest.permission.CALL_PHONE
                            ) != PackageManager.PERMISSION_GRANTED
                        ) {
                            ActivityCompat.requestPermissions(
                                context as Activity,
                                arrayOf(android.Manifest.permission.CALL_PHONE),
                                1
                            )
                        } else {
                            context.startActivity(intent)
                        }
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.Sms,
                    title = "Send SMS",
                    color = Color(0xFFFF9800),
                    onClick = { /* ACTION_SENDTO SMS */

                        val uri = "smsto:0737467020".toUri()

                        val intent = Intent(Intent.ACTION_SENDTO, uri)

                        intent.putExtra("Hello", "How is todays weather")

                        context.startActivity(intent)
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.Email,
                    title = "Send Email",
                    color = Color(0xFF9C27B0),
                    onClick = { /* ACTION_SENDTO Email */
                        val emailIntent =
                            Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "abc@gmail.com", null))
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject")

                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body")
                        context.startActivity(emailIntent)
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.Share,
                    title = "Share Content",
                    color = Color(0xFF607D8B),
                    onClick = { /* ACTION_SEND */
                        val shareIntent = Intent(Intent.ACTION_SEND)

                        shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

                        shareIntent.type = "text/plain"

                        shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey, download this app!")

                        context.startActivity(shareIntent)
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.Contacts,
                    title = "View Contacts",
                    color = Color(0xFF00BCD4),
                    onClick = { /* ACTION_VIEW Contacts */
                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            type = "vnd.android.cursor.dir/contact"
                        }
                        context.startActivity(intent)

                    }
                ),
                IntentFeature(
                    icon = Icons.Default.PersonAdd,
                    title = "Add Contact",
                    color = Color(0xFF795548),
                    onClick = { /* ACTION_INSERT_OR_EDIT */
                        val intent = Intent(Intent.ACTION_INSERT_OR_EDIT).apply {
                            type = ContactsContract.Contacts.CONTENT_ITEM_TYPE
                            // Optional pre-filled fields
                            putExtra(ContactsContract.Intents.Insert.NAME, "John Doe")
                            putExtra(ContactsContract.Intents.Insert.PHONE, "+254712345678")
                            putExtra(ContactsContract.Intents.Insert.EMAIL, "john.doe@example.com")
                        }
                        context.startActivity(intent)

                    }
                )
            )
        ),
        // Camera & Media
        IntentCategory(
            title = "📸 Camera & Media",
            features = listOf(
                IntentFeature(
                    icon = Icons.Default.PhotoCamera,
                    title = "Take Photo",
                    color = Color(0xFF4CAF50),
                    onClick = { /* MediaStore.ACTION_IMAGE_CAPTURE */
                        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

                        startActivityForResult(context as Activity,takePictureIntent,1,null)


                    }
                ),
                IntentFeature(
                    icon = Icons.Default.Videocam,
                    title = "Record Video",
                    color = Color(0xFF2196F3),
                    onClick = { /* MediaStore.ACTION_VIDEO_CAPTURE */
                        val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
                        context.startActivity(intent)
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.PhotoLibrary,
                    title = "Pick Photo",
                    color = Color(0xFFFF9800),
                    onClick = { /* ACTION_PICK */
                        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                        context.startActivity(intent)
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.FolderOpen,
                    title = "Open File",
                    color = Color(0xFF9C27B0),
                    onClick = { /* ACTION_GET_CONTENT */
                        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                            type = "*/*"
                        }
                        context.startActivity(intent)
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.Create,
                    title = "Create File",
                    color = Color(0xFF607D8B),
                    onClick = { /* ACTION_CREATE_DOCUMENT */
                        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_TITLE, "newfile.txt")
                        }
                        context.startActivity(intent)
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.Edit,
                    title = "Edit File",
                    color = Color(0xFF00BCD4),
                    onClick = { /* ACTION_EDIT */
                        val intent = Intent(Intent.ACTION_EDIT)
                        context.startActivity(intent)
                    }
                )
            )
        ),
        // Web & Browser
        IntentCategory(
            title = "🌐 Web & Browser",
            features = listOf(
                IntentFeature(
                    icon = Icons.Default.Language,
                    title = "Open URL",
                    color = Color(0xFF4CAF50),
                    onClick = { /* ACTION_VIEW URL */
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
                        context.startActivity(intent)
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.Search,
                    title = "Web Search",
                    color = Color(0xFF2196F3),
                    onClick = { /* ACTION_WEB_SEARCH */
                        val intent = Intent(Intent.ACTION_WEB_SEARCH).apply {
                            putExtra("query", "Android Intents example")
                        }
                        context.startActivity(intent)
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.Public,
                    title = "System Search",
                    color = Color(0xFFFF9800),
                    onClick = { /* ACTION_SEARCH */
                        val intent = Intent(Intent.ACTION_SEARCH)
                        context.startActivity(intent)
                    }
                )
            )
        ),
        // Maps & Navigation
        IntentCategory(
            title = "🗺️ Maps & Navigation",
            features = listOf(
                IntentFeature(
                    icon = Icons.Default.Map,
                    title = "Open Location",
                    color = Color(0xFF4CAF50),
                    onClick = { /* ACTION_VIEW geo */
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Nairobi"))
                        context.startActivity(intent)
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.Directions,
                    title = "Navigate To",
                    color = Color(0xFF2196F3),
                    onClick = { /* ACTION_VIEW navigation */
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=Kenyatta+University"))
                        context.startActivity(intent)
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.Streetview,
                    title = "Street View",
                    color = Color(0xFFFF9800),
                    onClick = { /* ACTION_VIEW streetview */
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("google.streetview:cbll=-1.286389,36.817223"))
                        context.startActivity(intent)
                    }
                )
            )
        ),
        // Device Settings
        IntentCategory(
            title = "⚙️ Device Settings",
            features = listOf(
                IntentFeature(
                    icon = Icons.Default.Settings,
                    title = "System Settings",
                    color = Color(0xFF4CAF50),
                    onClick = { /* ACTION_SETTINGS */
                        context.startActivity(Intent(Settings.ACTION_SETTINGS))
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.Wifi,
                    title = "WiFi Settings",
                    color = Color(0xFF2196F3),
                    onClick = { /* ACTION_WIFI_SETTINGS */
                        context.startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.Bluetooth,
                    title = "Bluetooth",
                    color = Color(0xFFFF9800),
                    onClick = { /* ACTION_BLUETOOTH_SETTINGS */
                        context.startActivity(Intent(Settings.ACTION_BLUETOOTH_SETTINGS))
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.LocationOn,
                    title = "Location Settings",
                    color = Color(0xFF9C27B0),
                    onClick = { /* ACTION_LOCATION_SOURCE_SETTINGS */
                        context.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.Apps,
                    title = "App Settings",
                    color = Color(0xFF607D8B),
                    onClick = { /* ACTION_APPLICATION_DETAILS_SETTINGS */
                        val intent = Intent(Settings.ACTION_APPLICATION_SETTINGS)
                        context.startActivity(intent)
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.DataUsage,
                    title = "Data Settings",
                    color = Color(0xFF00BCD4),
                    onClick = { /* ACTION_DATA_ROAMING_SETTINGS */
                        val intent = Intent(Settings.ACTION_DATA_ROAMING_SETTINGS)
                        context.startActivity(intent)
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.Storage,
                    title = "Storage Access",
                    color = Color(0xFF795548),
                    onClick = { /* ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION */
                        val intent = Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION).apply {
                            data = Uri.parse("package:${context.packageName}")
                        }
                        context.startActivity(intent)
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.BatteryStd,
                    title = "Battery Optimization",
                    color = Color(0xFFF44336),
                    onClick = { /* ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS */
                        val intent = Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS)
                        context.startActivity(intent)
                    }
                )
            )
        ),
        // Files & Documents
        IntentCategory(
            title = "📁 Files & Documents",
            features = listOf(
                IntentFeature(
                    icon = Icons.Default.Description,
                    title = "Open Document",
                    color = Color(0xFF4CAF50),
                    onClick = { /* ACTION_OPEN_DOCUMENT */
                        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                            addCategory(Intent.CATEGORY_OPENABLE)
                            type = "*/*"
                        }
                        context.startActivity(intent)
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.NoteAdd,
                    title = "Create Document",
                    color = Color(0xFF2196F3),
                    onClick = { /* ACTION_CREATE_DOCUMENT */
                        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
                            addCategory(Intent.CATEGORY_OPENABLE)
                            type = "text/plain"
                            putExtra(Intent.EXTRA_TITLE, "newfile.txt")
                        }
                        context.startActivity(intent)
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.Visibility,
                    title = "View File",
                    color = Color(0xFFFF9800),
                    onClick = { /* ACTION_VIEW file */
                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            type = "*/*"
                        }
                        context.startActivity(intent)
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.Build,
                    title = "Edit File",
                    color = Color(0xFF9C27B0),
                    onClick = { /* ACTION_EDIT */
                        val intent = Intent(Intent.ACTION_EDIT).apply {
                            type = "*/*"
                        }
                        context.startActivity(intent)
                    }
                )
            )
        ),
        // App & System Controls
        IntentCategory(
            title = "🧠 App & System Controls",
            features = listOf(
                IntentFeature(
                    icon = Icons.Default.Home,
                    title = "Launch App",
                    color = Color(0xFF4CAF50),
                    onClick = { /* ACTION_MAIN */
                        val intent = Intent(Intent.ACTION_MAIN).apply {
                            addCategory(Intent.CATEGORY_LAUNCHER)
                            // Optionally, launch a specific package:
                            // setPackage("com.android.chrome")
                        }
                        context.startActivity(intent)
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.PlayArrow,
                    title = "Run Service",
                    color = Color(0xFF2196F3),
                    onClick = { /* ACTION_RUN */
                        val intent = Intent(Intent.ACTION_RUN)
                        context.startActivity(intent)
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.InstallDesktop,
                    title = "Install APK",
                    color = Color(0xFFFF9800),
                    onClick = { /* ACTION_INSTALL_PACKAGE */
                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            type = "application/vnd.android.package-archive"
                            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        }
                        context.startActivity(intent)
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.Delete,
                    title = "Uninstall App",
                    color = Color(0xFF9C27B0),
                    onClick = { /* ACTION_DELETE */
                        val packageUri = Uri.parse("package:com.example.yourapp") // change this!
                        val intent = Intent(Intent.ACTION_DELETE, packageUri)
                        context.startActivity(intent)
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.Tune,
                    title = "App Preferences",
                    color = Color(0xFF607D8B),
                    onClick = { /* ACTION_APPLICATION_PREFERENCES */
                        val intent = Intent(Settings.ACTION_APPLICATION_SETTINGS)
                        context.startActivity(intent)
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.Notifications,
                    title = "Notification Settings",
                    color = Color(0xFF00BCD4),
                    onClick = { /* ACTION_APP_NOTIFICATION_SETTINGS */
                        val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                            putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
                        }
                        context.startActivity(intent)
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.BatterySaver,
                    title = "Battery Saver",
                    color = Color(0xFF795548),
                    onClick = { /* ACTION_BATTERY_SAVER_SETTINGS */
                        val intent = Intent(Settings.ACTION_BATTERY_SAVER_SETTINGS)
                        context.startActivity(intent)
                    }
                )
            )
        ),
        // Media & Audio
        IntentCategory(
            title = "🎵 Media & Audio",
            features = listOf(
                IntentFeature(
                    icon = Icons.Default.MusicNote,
                    title = "Music Player",
                    color = Color(0xFF4CAF50),
                    onClick = { /* ACTION_VIEW audio */
                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            type = "audio/*"
                        }
                        context.startActivity(intent)
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.VideoLibrary,
                    title = "Video Player",
                    color = Color(0xFF2196F3),
                    onClick = { /* ACTION_VIEW video */
                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            type = "video/*"
                        }
                        context.startActivity(intent)
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.PhotoAlbum,
                    title = "Photo Gallery",
                    color = Color(0xFFFF9800),
                    onClick = { /* ACTION_VIEW images */
                        val intent = Intent(Intent.ACTION_VIEW, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                        context.startActivity(intent)
                    }
                )
            )
        ),
        // System Utilities
        IntentCategory(
            title = "🧩 System Utilities",
            features = listOf(
                IntentFeature(
                    icon = Icons.Default.BatteryFull,
                    title = "Battery Usage",
                    color = Color(0xFF4CAF50),
                    onClick = { /* ACTION_POWER_USAGE_SUMMARY */
                        context.startActivity(Intent(Intent.ACTION_POWER_USAGE_SUMMARY))
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.SdStorage,
                    title = "Storage Info",
                    color = Color(0xFF2196F3),
                    onClick = { /* ACTION_MEMORY_CARD_SETTINGS */
                        val intent = Intent(Settings.ACTION_MEMORY_CARD_SETTINGS)
                        context.startActivity(intent)
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.Layers,
                    title = "Overlay Permission",
                    color = Color(0xFFFF9800),
                    onClick = { /* ACTION_MANAGE_OVERLAY_PERMISSION */
                        val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
                        context.startActivity(intent)
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.SettingsInputComponent,
                    title = "Write Settings",
                    color = Color(0xFF9C27B0),
                    onClick = { /* ACTION_MANAGE_WRITE_SETTINGS */
                        val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
                        context.startActivity(intent)
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.AirplanemodeActive,
                    title = "Airplane Mode",
                    color = Color(0xFF607D8B),
                    onClick = { /* ACTION_AIRPLANE_MODE_SETTINGS */
                        val intent = Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS)
                        context.startActivity(intent)
                    }
                ),
                IntentFeature(
                    icon = Icons.Default.AttachMoney,
                    title = "STK Push",
                    color =Color(0xFF00FF00),
                            onClick = { /* ACTION_AIRPLANE_MODE_SETTINGS */
                                val simToolKitLaunchIntent =
                                    context.packageManager.getLaunchIntentForPackage("com.android.stk")

                                simToolKitLaunchIntent?.let { context.startActivity(it) }


                            }
                )

            )

        )
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Intent Features",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF667EEA),
                            Color(0xFF764BA2)
                        )
                    )
                )
        ) {
            // Header
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Android Intent Capabilities",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Explore all available intent actions and system features",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            }

            // Categories List
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                items(categories) { category ->
                    IntentCategorySection(category = category)
                }

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

@Composable
fun IntentCategorySection(category: IntentCategory) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(20.dp)
            ),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Category Title
            Text(
                text = category.title,
                color = Color(0xFF333333),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Features Grid
            val chunkedFeatures = category.features.chunked(3)
            chunkedFeatures.forEach { rowFeatures ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    rowFeatures.forEach { feature ->
                        IntentFeatureItem(
                            feature = feature,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    // Fill remaining space if row has less than 3 items
                    repeat(3 - rowFeatures.size) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun IntentFeatureItem(
    feature: IntentFeature,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { feature.onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Icon Container
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            feature.color,
                            feature.color.copy(alpha = 0.7f)
                        )
                    )
                )
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = feature.icon,
                contentDescription = feature.title,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Feature Title
        Text(
            text = feature.title,
            color = Color(0xFF333333),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            maxLines = 2,
            lineHeight = 14.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun IntentFeaturesScreenPreview() {
    MaterialTheme {
        IntentFeaturesScreen()
    }
}