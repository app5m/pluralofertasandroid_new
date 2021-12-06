package br.com.app5m.pluralofertas.util

import java.text.NumberFormat
import java.util.*

object CashConverter {

    //Recebe o valor do cupom (em moedas) e retorna no formato monetário
    fun getInCash(value: Double): String {

        var ptBr = Locale("pt", "BR")
        var valueString = NumberFormat.getCurrencyInstance(ptBr).format((value / 100))
        valueString = unmask(valueString)

        return valueString

    }

    //Função para retirar mascara de formatação
    private fun unmask(s: String): String {
        return s.replace("[R$]".toRegex(), "")
    }

}