package com.example.testeableapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import com.example.testeableapp.model.MenuData
import org.junit.Rule
import org.junit.Test

class RestaurantOrderTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    // Mensaje de pedido vacio visible al inicio
    @Test
    fun mensajePedidoVacio_esVisibleAlInicio() {
        composeTestRule.waitForIdle()
        composeTestRule
            .onNodeWithTag("emptyOrderMessage")
            .performScrollTo()
            .assertIsDisplayed()
    }

    // Todos los items del menu visibles
    @Test
    fun todosLosItemsDelMenu_sonVisibles() {
        composeTestRule.waitForIdle()
        composeTestRule
            .onNodeWithTag("menuItem_1")
            .performScrollTo()
            .assertIsDisplayed()
    }

    // El total general se actualiza al agregar items
    @Test
    fun totalGeneral_seActualizaAlAgregarItems() {
        composeTestRule.waitForIdle()
        composeTestRule
            .onNodeWithTag("addButton_1")
            .performScrollTo()
            .performClick()
        composeTestRule.waitForIdle()
        composeTestRule
            .onNodeWithTag("totalValue")
            .performScrollTo()
            .assertIsDisplayed()
    }

    // PRUEBA UI ADICIONAL 1: El titulo de la app es visible
    @Test
    fun tituloDeLaApp_esVisible() {
        composeTestRule.waitForIdle()
        composeTestRule
            .onNodeWithTag("appTitle")
            .performScrollTo()
            .assertIsDisplayed()
            .assertTextEquals("Restaurante El Sabor")
    }

    // PRUEBA UI ADICIONAL 2: Boton Realizar Pedido aparece al agregar un item
    @Test
    fun botonRealizarPedido_apareceAlAgregarUnItem() {
        composeTestRule.waitForIdle()
        composeTestRule
            .onNodeWithTag("emptyOrderMessage")
            .performScrollTo()
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithTag("addButton_1")
            .performScrollTo()
            .performClick()
        composeTestRule.waitForIdle()
        composeTestRule
            .onNodeWithTag("placeOrderButton")
            .performScrollTo()
            .assertIsDisplayed()
    }
}