package com.daniel.estrada.mobilewellnessdapp.data

import android.app.Application
import android.content.Context
import com.daniel.estrada.mobilewellnessdapp.R
import com.daniel.estrada.mobilewellnessdapp.smartcontracts.BowheadDevChallenge
import com.daniel.estrada.mobilewellnessdapp.utils.BASE_URL
import com.daniel.estrada.mobilewellnessdapp.utils.CONTRACT_ADDRESS
import com.daniel.estrada.mobilewellnessdapp.utils.MASTER_ACCOUNT_PK
import io.reactivex.Flowable
import org.web3j.crypto.Credentials
import org.web3j.crypto.WalletUtils
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.response.TransactionReceipt
import org.web3j.protocol.http.HttpService
import org.web3j.tx.Transfer
import org.web3j.tx.gas.ContractGasProvider
import org.web3j.utils.Convert
import java.io.File
import java.math.BigDecimal
import java.math.BigInteger

class Repository private constructor(val context: Application){

    private val web3j = Web3j.build(HttpService("$BASE_URL"))
    private var credentials: Credentials? = null
    private val gasProvider = object: ContractGasProvider {
        val GAS_PRICE = BigInteger.valueOf(20000000000)
        val GAS_LIMIT = BigInteger.valueOf(6721975)

        override fun getGasPrice(contractFunc: String?): BigInteger = GAS_PRICE
        override fun getGasPrice(): BigInteger = GAS_PRICE
        override fun getGasLimit(contractFunc: String?): BigInteger = GAS_LIMIT
        override fun getGasLimit(): BigInteger = GAS_LIMIT
    }

    fun createWallet(password: String) {
        val sharedPref = context.getSharedPreferences(
            context.getString(R.string.preference_app_data),
            Context.MODE_PRIVATE
        )
        val path = context.filesDir.absolutePath
        val fileName = WalletUtils.generateNewWalletFile(password, File(path))
        with (sharedPref.edit()) {
            putString(context.getString(R.string.wallet_directory), "$path/$fileName")
            putString(context.getString(R.string.saved_password), password)
            putBoolean(context.getString(R.string.is_first_experience), false)
            apply()
        }
    }

    private fun loadCredentials(): Credentials? {
        if (credentials == null) {
            val sharedPref = context.getSharedPreferences(
                context.getString(R.string.preference_app_data),
                Context.MODE_PRIVATE
            )

            val walletDir = sharedPref.getString(context.getString(R.string.wallet_directory), null)
            val password = sharedPref.getString(context.getString(R.string.saved_password), null)
            credentials = WalletUtils.loadCredentials(password, File(walletDir))
        }
        return credentials
    }

    fun sendFunds(): TransactionReceipt? {
        return Transfer.sendFunds(
            web3j,
            Credentials.create(MASTER_ACCOUNT_PK),
            loadCredentials()!!.address,
            BigDecimal(500000000000000000),
            Convert.Unit.WEI
        ).send()
    }

    fun registerUser(): TransactionReceipt? {
        val contract = BowheadDevChallenge.load(
            CONTRACT_ADDRESS,
            web3j,
            loadCredentials(),
            gasProvider
        )
        return contract.registerUser(BigInteger.valueOf(1)).send()
    }

    fun addHealthData(data: ByteArray) {
        val contract = BowheadDevChallenge.load(
            CONTRACT_ADDRESS,
            web3j,
            loadCredentials(),
            gasProvider
        )

        contract.addHealthData(data).send()
    }

    fun getHealthData(): MutableList<Any?>? {
        val contract = BowheadDevChallenge.load(
            CONTRACT_ADDRESS,
            web3j,
            loadCredentials(),
            gasProvider
        )

        return contract.healthData.send()
    }

    fun newEarningsEvent(): Flowable<BowheadDevChallenge.NewUserEarningsEventResponse>? {
        val contract = BowheadDevChallenge.load(
            CONTRACT_ADDRESS,
            web3j,
            loadCredentials(),
            gasProvider
        )

        return contract.newUserEarningsEventFlowable(
            DefaultBlockParameterName.EARLIEST,
            DefaultBlockParameterName.LATEST
        )
    }

    companion object {
        private var INSTANCE: Repository? = null

        fun getInstance(context: Application): Repository {
            return INSTANCE ?: Repository(context).also { INSTANCE = it }
        }
    }
}