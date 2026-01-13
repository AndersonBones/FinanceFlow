package br.edu.utfpr.financeflow.database

import br.edu.utfpr.financeflow.model.Movimentacao
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.tasks.await

class MovimentacaoDatabaseHandler {

    private val firestore = Firebase.firestore

    companion object {
        private const val COLLECTION_NAME = "movimentacoes"

        @Volatile
        private var INSTANCE: MovimentacaoDatabaseHandler? = null

        fun getInstance(): MovimentacaoDatabaseHandler {
            if (INSTANCE == null) {
                INSTANCE = MovimentacaoDatabaseHandler()
            }
            return INSTANCE!!
        }
    }

    suspend fun inserir(movimentacao: Movimentacao) {
        firestore
            .collection(COLLECTION_NAME)
            .add(movimentacao)
            .await()
    }

    suspend fun listar(): List<Movimentacao> {
        val snapshot = firestore
            .collection(COLLECTION_NAME)
            .get()
            .await()

        return snapshot.toObjects()
    }
}
