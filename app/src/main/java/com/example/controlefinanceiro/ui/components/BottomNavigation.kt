package com.example.controlefinanceiro.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.controlefinanceiro.ui.theme.BlackBackground
import com.example.controlefinanceiro.ui.theme.YellowText

@Composable
fun BottomNavigationBar(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    NavigationBar(
        containerColor = BlackBackground,
        contentColor = YellowText
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = currentRoute == "home",
            onClick = { onNavigate("home") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = YellowText,
                selectedTextColor = YellowText,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Receipt, contentDescription = "Receitas") },
            label = { Text("Receitas") },
            selected = currentRoute == "receita",
            onClick = { onNavigate("receita") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = YellowText,
                selectedTextColor = YellowText,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Add, contentDescription = "Contas") },
            label = { Text("Contas") },
            selected = currentRoute == "conta",
            onClick = { onNavigate("conta") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = YellowText,
                selectedTextColor = YellowText,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.CreditCard, contentDescription = "Cartões") },
            label = { Text("Cartões") },
            selected = currentRoute == "cartao",
            onClick = { onNavigate("cartao") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = YellowText,
                selectedTextColor = YellowText,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Analytics, contentDescription = "Relatórios") },
            label = { Text("Relatórios") },
            selected = currentRoute == "reports",
            onClick = { onNavigate("reports") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = YellowText,
                selectedTextColor = YellowText,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
    }
}
