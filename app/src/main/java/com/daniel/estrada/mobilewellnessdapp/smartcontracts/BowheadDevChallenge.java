package com.daniel.estrada.mobilewellnessdapp.smartcontracts;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.0.
 */
@SuppressWarnings("rawtypes")
public class BowheadDevChallenge extends Contract {
    public static final String BINARY = "0x608060405234801561001057600080fd5b506105ca806100206000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c80631bbfae0e14610046578063919932d214610074578063e2329539146100d3575b600080fd5b6100726004803603602081101561005c57600080fd5b8101908080359060200190929190505050610101565b005b61007c610228565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b838110156100bf5780820151818401526020810190506100a4565b505050509050019250505060405180910390f35b6100ff600480360360208110156100e957600080fd5b8101908080359060200190929190505050610377565b005b60008111610177576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260208152602001807f546865206c6576656c206d7573742062652067726561746572207468616e203081525060200191505060405180910390fd5b806000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055507fe29d35093005f4d575e1003753426b57a7f64378ba73332eef9c6ccc2b8decd63382604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019250505060405180910390a150565b606060008060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050600081116102e3576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260178152602001807f546865207573657220646f6573206e6f7420657869737400000000000000000081525060200191505060405180910390fd5b600260003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002080548060200260200160405190810160405280929190818152602001828054801561036c57602002820191906000526020600020905b815481526020019060010190808311610358575b505050505091505090565b60008060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905060008111610430576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260178152602001807f546865207573657220646f6573206e6f7420657869737400000000000000000081525060200191505060405180910390fd5b600060018060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205401905080600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550600260003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208390806001815401808255809150509060018203906000526020600020016000909192909190915055507fe5757054728aef6cf2a9fad0ec3f084d682d2236659f4251f1e3f1f189b65ecc33828402604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019250505060405180910390a150505056fea265627a7a72315820fecf1ded2f636b8a985e04d0aa5e2b8a1d0fd4b342c10233c4d92047605a7d9364736f6c63430005100032";

    public static final String FUNC_REGISTERUSER = "registerUser";

    public static final String FUNC_ADDHEALTHDATA = "addHealthData";

    public static final String FUNC_GETHEALTHDATA = "getHealthData";

    public static final Event NEWUSEREARNINGS_EVENT = new Event("NewUserEarnings",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event USERREGISTERED_EVENT = new Event("UserRegistered",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("4", "0xc7c44c64DBddA29e2723FD1056ab876A54ef24CE");
        _addresses.put("1611110989908", "0xDE5a34b81B0Efa4773B5097f7676f4fFe9F79f42");
    }

    @Deprecated
    protected BowheadDevChallenge(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected BowheadDevChallenge(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected BowheadDevChallenge(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected BowheadDevChallenge(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<NewUserEarningsEventResponse> getNewUserEarningsEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NEWUSEREARNINGS_EVENT, transactionReceipt);
        ArrayList<NewUserEarningsEventResponse> responses = new ArrayList<NewUserEarningsEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NewUserEarningsEventResponse typedResponse = new NewUserEarningsEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.user = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.earning = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<NewUserEarningsEventResponse> newUserEarningsEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, NewUserEarningsEventResponse>() {
            @Override
            public NewUserEarningsEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(NEWUSEREARNINGS_EVENT, log);
                NewUserEarningsEventResponse typedResponse = new NewUserEarningsEventResponse();
                typedResponse.log = log;
                typedResponse.user = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.earning = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<NewUserEarningsEventResponse> newUserEarningsEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWUSEREARNINGS_EVENT));
        return newUserEarningsEventFlowable(filter);
    }

    public List<UserRegisteredEventResponse> getUserRegisteredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(USERREGISTERED_EVENT, transactionReceipt);
        ArrayList<UserRegisteredEventResponse> responses = new ArrayList<UserRegisteredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            UserRegisteredEventResponse typedResponse = new UserRegisteredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.user = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.level = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<UserRegisteredEventResponse> userRegisteredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, UserRegisteredEventResponse>() {
            @Override
            public UserRegisteredEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(USERREGISTERED_EVENT, log);
                UserRegisteredEventResponse typedResponse = new UserRegisteredEventResponse();
                typedResponse.log = log;
                typedResponse.user = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.level = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<UserRegisteredEventResponse> userRegisteredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(USERREGISTERED_EVENT));
        return userRegisteredEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> registerUser(BigInteger level) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REGISTERUSER,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(level)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addHealthData(byte[] data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADDHEALTHDATA,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(data)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List> getHealthData() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETHEALTHDATA,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Bytes32>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    @Deprecated
    public static BowheadDevChallenge load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new BowheadDevChallenge(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static BowheadDevChallenge load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new BowheadDevChallenge(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static BowheadDevChallenge load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new BowheadDevChallenge(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static BowheadDevChallenge load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new BowheadDevChallenge(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<BowheadDevChallenge> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(BowheadDevChallenge.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<BowheadDevChallenge> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(BowheadDevChallenge.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<BowheadDevChallenge> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(BowheadDevChallenge.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<BowheadDevChallenge> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(BowheadDevChallenge.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class NewUserEarningsEventResponse extends BaseEventResponse {
        public String user;

        public BigInteger earning;
    }

    public static class UserRegisteredEventResponse extends BaseEventResponse {
        public String user;

        public BigInteger level;
    }
}
