package ar.edu.itba.tp1g5;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.0)",
    comments = "Source: administration/waitingRoom.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class WaitingRoomServiceGrpc {

  private WaitingRoomServiceGrpc() {}

  public static final String SERVICE_NAME = "waitingroom.WaitingRoomService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ar.edu.itba.tp1g5.PatientRequest,
      ar.edu.itba.tp1g5.PatientResponse> getRegisterPatientMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RegisterPatient",
      requestType = ar.edu.itba.tp1g5.PatientRequest.class,
      responseType = ar.edu.itba.tp1g5.PatientResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ar.edu.itba.tp1g5.PatientRequest,
      ar.edu.itba.tp1g5.PatientResponse> getRegisterPatientMethod() {
    io.grpc.MethodDescriptor<ar.edu.itba.tp1g5.PatientRequest, ar.edu.itba.tp1g5.PatientResponse> getRegisterPatientMethod;
    if ((getRegisterPatientMethod = WaitingRoomServiceGrpc.getRegisterPatientMethod) == null) {
      synchronized (WaitingRoomServiceGrpc.class) {
        if ((getRegisterPatientMethod = WaitingRoomServiceGrpc.getRegisterPatientMethod) == null) {
          WaitingRoomServiceGrpc.getRegisterPatientMethod = getRegisterPatientMethod =
              io.grpc.MethodDescriptor.<ar.edu.itba.tp1g5.PatientRequest, ar.edu.itba.tp1g5.PatientResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RegisterPatient"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ar.edu.itba.tp1g5.PatientRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ar.edu.itba.tp1g5.PatientResponse.getDefaultInstance()))
              .setSchemaDescriptor(new WaitingRoomServiceMethodDescriptorSupplier("RegisterPatient"))
              .build();
        }
      }
    }
    return getRegisterPatientMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ar.edu.itba.tp1g5.PatientRequest,
      ar.edu.itba.tp1g5.PatientResponse> getUpdateEmergencyLevelMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateEmergencyLevel",
      requestType = ar.edu.itba.tp1g5.PatientRequest.class,
      responseType = ar.edu.itba.tp1g5.PatientResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ar.edu.itba.tp1g5.PatientRequest,
      ar.edu.itba.tp1g5.PatientResponse> getUpdateEmergencyLevelMethod() {
    io.grpc.MethodDescriptor<ar.edu.itba.tp1g5.PatientRequest, ar.edu.itba.tp1g5.PatientResponse> getUpdateEmergencyLevelMethod;
    if ((getUpdateEmergencyLevelMethod = WaitingRoomServiceGrpc.getUpdateEmergencyLevelMethod) == null) {
      synchronized (WaitingRoomServiceGrpc.class) {
        if ((getUpdateEmergencyLevelMethod = WaitingRoomServiceGrpc.getUpdateEmergencyLevelMethod) == null) {
          WaitingRoomServiceGrpc.getUpdateEmergencyLevelMethod = getUpdateEmergencyLevelMethod =
              io.grpc.MethodDescriptor.<ar.edu.itba.tp1g5.PatientRequest, ar.edu.itba.tp1g5.PatientResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateEmergencyLevel"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ar.edu.itba.tp1g5.PatientRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ar.edu.itba.tp1g5.PatientResponse.getDefaultInstance()))
              .setSchemaDescriptor(new WaitingRoomServiceMethodDescriptorSupplier("UpdateEmergencyLevel"))
              .build();
        }
      }
    }
    return getUpdateEmergencyLevelMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ar.edu.itba.tp1g5.PatientRequest,
      ar.edu.itba.tp1g5.PatientResponse> getCheckWaitingTimeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CheckWaitingTime",
      requestType = ar.edu.itba.tp1g5.PatientRequest.class,
      responseType = ar.edu.itba.tp1g5.PatientResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ar.edu.itba.tp1g5.PatientRequest,
      ar.edu.itba.tp1g5.PatientResponse> getCheckWaitingTimeMethod() {
    io.grpc.MethodDescriptor<ar.edu.itba.tp1g5.PatientRequest, ar.edu.itba.tp1g5.PatientResponse> getCheckWaitingTimeMethod;
    if ((getCheckWaitingTimeMethod = WaitingRoomServiceGrpc.getCheckWaitingTimeMethod) == null) {
      synchronized (WaitingRoomServiceGrpc.class) {
        if ((getCheckWaitingTimeMethod = WaitingRoomServiceGrpc.getCheckWaitingTimeMethod) == null) {
          WaitingRoomServiceGrpc.getCheckWaitingTimeMethod = getCheckWaitingTimeMethod =
              io.grpc.MethodDescriptor.<ar.edu.itba.tp1g5.PatientRequest, ar.edu.itba.tp1g5.PatientResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckWaitingTime"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ar.edu.itba.tp1g5.PatientRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ar.edu.itba.tp1g5.PatientResponse.getDefaultInstance()))
              .setSchemaDescriptor(new WaitingRoomServiceMethodDescriptorSupplier("CheckWaitingTime"))
              .build();
        }
      }
    }
    return getCheckWaitingTimeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static WaitingRoomServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<WaitingRoomServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<WaitingRoomServiceStub>() {
        @java.lang.Override
        public WaitingRoomServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new WaitingRoomServiceStub(channel, callOptions);
        }
      };
    return WaitingRoomServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static WaitingRoomServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<WaitingRoomServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<WaitingRoomServiceBlockingStub>() {
        @java.lang.Override
        public WaitingRoomServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new WaitingRoomServiceBlockingStub(channel, callOptions);
        }
      };
    return WaitingRoomServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static WaitingRoomServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<WaitingRoomServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<WaitingRoomServiceFutureStub>() {
        @java.lang.Override
        public WaitingRoomServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new WaitingRoomServiceFutureStub(channel, callOptions);
        }
      };
    return WaitingRoomServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void registerPatient(ar.edu.itba.tp1g5.PatientRequest request,
        io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.PatientResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRegisterPatientMethod(), responseObserver);
    }

    /**
     */
    default void updateEmergencyLevel(ar.edu.itba.tp1g5.PatientRequest request,
        io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.PatientResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateEmergencyLevelMethod(), responseObserver);
    }

    /**
     */
    default void checkWaitingTime(ar.edu.itba.tp1g5.PatientRequest request,
        io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.PatientResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckWaitingTimeMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service WaitingRoomService.
   */
  public static abstract class WaitingRoomServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return WaitingRoomServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service WaitingRoomService.
   */
  public static final class WaitingRoomServiceStub
      extends io.grpc.stub.AbstractAsyncStub<WaitingRoomServiceStub> {
    private WaitingRoomServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WaitingRoomServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new WaitingRoomServiceStub(channel, callOptions);
    }

    /**
     */
    public void registerPatient(ar.edu.itba.tp1g5.PatientRequest request,
        io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.PatientResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRegisterPatientMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateEmergencyLevel(ar.edu.itba.tp1g5.PatientRequest request,
        io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.PatientResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateEmergencyLevelMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void checkWaitingTime(ar.edu.itba.tp1g5.PatientRequest request,
        io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.PatientResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckWaitingTimeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service WaitingRoomService.
   */
  public static final class WaitingRoomServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<WaitingRoomServiceBlockingStub> {
    private WaitingRoomServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WaitingRoomServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new WaitingRoomServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public ar.edu.itba.tp1g5.PatientResponse registerPatient(ar.edu.itba.tp1g5.PatientRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRegisterPatientMethod(), getCallOptions(), request);
    }

    /**
     */
    public ar.edu.itba.tp1g5.PatientResponse updateEmergencyLevel(ar.edu.itba.tp1g5.PatientRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateEmergencyLevelMethod(), getCallOptions(), request);
    }

    /**
     */
    public ar.edu.itba.tp1g5.PatientResponse checkWaitingTime(ar.edu.itba.tp1g5.PatientRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckWaitingTimeMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service WaitingRoomService.
   */
  public static final class WaitingRoomServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<WaitingRoomServiceFutureStub> {
    private WaitingRoomServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WaitingRoomServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new WaitingRoomServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ar.edu.itba.tp1g5.PatientResponse> registerPatient(
        ar.edu.itba.tp1g5.PatientRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRegisterPatientMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ar.edu.itba.tp1g5.PatientResponse> updateEmergencyLevel(
        ar.edu.itba.tp1g5.PatientRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateEmergencyLevelMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ar.edu.itba.tp1g5.PatientResponse> checkWaitingTime(
        ar.edu.itba.tp1g5.PatientRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckWaitingTimeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER_PATIENT = 0;
  private static final int METHODID_UPDATE_EMERGENCY_LEVEL = 1;
  private static final int METHODID_CHECK_WAITING_TIME = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTER_PATIENT:
          serviceImpl.registerPatient((ar.edu.itba.tp1g5.PatientRequest) request,
              (io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.PatientResponse>) responseObserver);
          break;
        case METHODID_UPDATE_EMERGENCY_LEVEL:
          serviceImpl.updateEmergencyLevel((ar.edu.itba.tp1g5.PatientRequest) request,
              (io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.PatientResponse>) responseObserver);
          break;
        case METHODID_CHECK_WAITING_TIME:
          serviceImpl.checkWaitingTime((ar.edu.itba.tp1g5.PatientRequest) request,
              (io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.PatientResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getRegisterPatientMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              ar.edu.itba.tp1g5.PatientRequest,
              ar.edu.itba.tp1g5.PatientResponse>(
                service, METHODID_REGISTER_PATIENT)))
        .addMethod(
          getUpdateEmergencyLevelMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              ar.edu.itba.tp1g5.PatientRequest,
              ar.edu.itba.tp1g5.PatientResponse>(
                service, METHODID_UPDATE_EMERGENCY_LEVEL)))
        .addMethod(
          getCheckWaitingTimeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              ar.edu.itba.tp1g5.PatientRequest,
              ar.edu.itba.tp1g5.PatientResponse>(
                service, METHODID_CHECK_WAITING_TIME)))
        .build();
  }

  private static abstract class WaitingRoomServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    WaitingRoomServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ar.edu.itba.tp1g5.WaitingRoomServiceModel.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("WaitingRoomService");
    }
  }

  private static final class WaitingRoomServiceFileDescriptorSupplier
      extends WaitingRoomServiceBaseDescriptorSupplier {
    WaitingRoomServiceFileDescriptorSupplier() {}
  }

  private static final class WaitingRoomServiceMethodDescriptorSupplier
      extends WaitingRoomServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    WaitingRoomServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (WaitingRoomServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new WaitingRoomServiceFileDescriptorSupplier())
              .addMethod(getRegisterPatientMethod())
              .addMethod(getUpdateEmergencyLevelMethod())
              .addMethod(getCheckWaitingTimeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
