package com.example.testeableapp

import com.example.testeableapp.model.MenuData
import org.junit.Assert.*
import org.junit.Test

class ExampleUnitTest {

    // Agregar item al pedido
    @Test
    fun agregarItemAlPedido_incrementaCantidad() {
        val quantities = mutableMapOf<Int, Int>()
        val itemId = 1

        quantities[itemId] = (quantities[itemId] ?: 0) + 1

        assertEquals(1, quantities[itemId])
    }

    // Incrementar y decrementar cantidad
    @Test
    fun incrementarYDecrementar_funcionaCorrectamente() {
        val quantities = mutableMapOf<Int, Int>()
        val itemId = 2

        quantities[itemId] = (quantities[itemId] ?: 0) + 1
        quantities[itemId] = (quantities[itemId] ?: 0) + 1
        assertEquals(2, quantities[itemId])

        val currentQty = quantities[itemId] ?: 0
        if (currentQty > 1) {
            quantities[itemId] = currentQty - 1
        } else {
            quantities.remove(itemId)
        }
        assertEquals(1, quantities[itemId])
    }

    // Eliminar item al decrementar desde 1
    @Test
    fun decrementarDesde1_eliminaElItem() {
        val quantities = mutableMapOf<Int, Int>()
        val itemId = 3

        quantities[itemId] = 1

        val currentQty = quantities[itemId] ?: 0
        if (currentQty <= 1) {
            quantities.remove(itemId)
        } else {
            quantities[itemId] = currentQty - 1
        }

        assertNull(quantities[itemId])
        assertFalse(quantities.containsKey(itemId))
    }

    // Calculo del total a pagar
    @Test
    fun calcularTotal_formulaEsCorrecta() {
        val quantities = mapOf(1 to 2, 7 to 3)
        val expectedTotal = 20.00

        val calculatedTotal = quantities.entries.sumOf { (id, qty) ->
            (MenuData.items.find { it.id == id }?.price ?: 0.0) * qty
        }

        assertEquals(expectedTotal, calculatedTotal, 0.001)
    }

    // PRUEBA ADICIONAL 1: Verificar que el menu tiene exactamente 10 items
    @Test
    fun menuData_contieneExactamenteDiezItems() {
        val totalItems = MenuData.items.size
        assertEquals(10, totalItems)
    }

    // PRUEBA ADICIONAL 2: Agregar mismo item varias veces acumula cantidad
    @Test
    fun agregarMismoItemVariasVeces_acumulaCantidadSinDuplicados() {
        val quantities = mutableMapOf<Int, Int>()
        val itemId = 5

        repeat(3) {
            quantities[itemId] = (quantities[itemId] ?: 0) + 1
        }

        assertEquals(1, quantities.keys.size)
        assertEquals(3, quantities[itemId])
    }
}