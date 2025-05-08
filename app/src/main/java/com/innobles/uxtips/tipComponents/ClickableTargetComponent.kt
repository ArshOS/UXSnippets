package com.innobles.uxtips.tipComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.innobles.uxtips.ui.theme.WhiteGreekVilla
import com.innobles.uxtips.ui.theme.WhiteGreekVillaBar
import com.innobles.uxtips.ui.theme.WhiteGreekVillaItem


@Composable
fun ClickableTargetComposable() {
    Scaffold(
        topBar = { TopBarComposable() },
        content = { paddingValues ->
            Surface(modifier = Modifier.padding(paddingValues)) {

            }
        },
        bottomBar = { BottomBarComposable() },
        containerColor = WhiteGreekVilla
    )
}

@Composable
fun TopBarComposable() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(WhiteGreekVillaBar)
            .padding(all = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { }) {
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                tint = WhiteGreekVillaItem
            )
        }

        Text(
            text = "Activity",
            color = WhiteGreekVillaItem,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f)
        )


        // ❌ Case 1: Using Icon with clickable modifier
        // This approach provides a *smaller tappable target area* limited to the icon's size,
        // which can lead to usability issues and does not meet accessibility guidelines.
        Icon(
            modifier = Modifier.clickable { },
            imageVector = Icons.Default.Notifications,
            contentDescription = "Search",
            tint = WhiteGreekVillaItem
        )

        // ✅ Case 2: Using IconButton for better touch target and accessibility
        // IconButton provides a *larger clickable area* (typically 48x48 dp),
        // ensuring better usability and adherence to Material Design standards.
        // Or add padding withing the clickable area.
        IconButton(onClick = { }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More",
                tint = WhiteGreekVillaItem
            )
        }
    }
}


@Composable
fun BottomBarComposable() {
}

@Preview(
    showBackground = true,
    widthDp = 360,
    heightDp = 800
)
@Composable
private fun GreetingPreview() {
    ClickableTargetComposable()
}