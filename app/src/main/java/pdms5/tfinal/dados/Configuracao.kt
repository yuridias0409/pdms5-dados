package pdms5.tfinal.dados

import android.os.Parcelable

@Parcelize
data class Configuracao(val numeroDados: Int, val numeroFaces: Int): Parcelable
