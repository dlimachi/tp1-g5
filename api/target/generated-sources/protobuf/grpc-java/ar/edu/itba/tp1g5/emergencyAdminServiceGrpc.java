package ar.edu.itba.tp1g5;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.0)",
    comments = "Source: administration/emergencyAdmin.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class emergencyAdminServiceGrpc {

  private emergencyAdminServiceGrpc() {}

  public static final String SERVICE_NAME = "administration.emergencyAdminService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      ar.edu.itba.tp1g5.RoomResponse> getAddRoomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddRoom",
      requestType = com.google.protobuf.Empty.class,
      responseType = ar.edu.itba.tp1g5.RoomResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      ar.edu.itba.tp1g5.RoomResponse> getAddRoomMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, ar.edu.itba.tp1g5.RoomResponse> getAddRoomMethod;
    if ((getAddRoomMethod = emergencyAdminServiceGrpc.getAddRoomMethod) == null) {
      synchronized (emergencyAdminServiceGrpc.class) {
        if ((getAddRoomMethod = emergencyAdminServiceGrpc.getAddRoomMethod) == null) {
          emergencyAdminServiceGrpc.getAddRoomMethod = getAddRoomMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, ar.edu.itba.tp1g5.RoomResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddRoom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ar.edu.itba.tp1g5.RoomResponse.getDefaultInstance()))
              .setSchemaDescriptor(new emergencyAdminServiceMethodDescriptorSupplier("AddRoom"))
              .build();
        }
      }
    }
    return getAddRoomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ar.edu.itba.tp1g5.DoctorRequest,
      ar.edu.itba.tp1g5.DoctorResponse> getAddDoctorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddDoctor",
      requestType = ar.edu.itba.tp1g5.DoctorRequest.class,
      responseType = ar.edu.itba.tp1g5.DoctorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ar.edu.itba.tp1g5.DoctorRequest,
      ar.edu.itba.tp1g5.DoctorResponse> getAddDoctorMethod() {
    io.grpc.MethodDescriptor<ar.edu.itba.tp1g5.DoctorRequest, ar.edu.itba.tp1g5.DoctorResponse> getAddDoctorMethod;
    if ((getAddDoctorMethod = emergencyAdminServiceGrpc.getAddDoctorMethod) == null) {
      synchronized (emergencyAdminServiceGrpc.class) {
        if ((getAddDoctorMethod = emergencyAdminServiceGrpc.getAddDoctorMethod) == null) {
          emergencyAdminServiceGrpc.getAddDoctorMethod = getAddDoctorMethod =
              io.grpc.MethodDescriptor.<ar.edu.itba.tp1g5.DoctorRequest, ar.edu.itba.tp1g5.DoctorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddDoctor"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ar.edu.itba.tp1g5.DoctorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ar.edu.itba.tp1g5.DoctorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new emergencyAdminServiceMethodDescriptorSupplier("AddDoctor"))
              .build();
        }
      }
    }
    return getAddDoctorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ar.edu.itba.tp1g5.DoctorRequest,
      ar.edu.itba.tp1g5.DoctorResponse> getSetDoctorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetDoctor",
      requestType = ar.edu.itba.tp1g5.DoctorRequest.class,
      responseType = ar.edu.itba.tp1g5.DoctorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ar.edu.itba.tp1g5.DoctorRequest,
      ar.edu.itba.tp1g5.DoctorResponse> getSetDoctorMethod() {
    io.grpc.MethodDescriptor<ar.edu.itba.tp1g5.DoctorRequest, ar.edu.itba.tp1g5.DoctorResponse> getSetDoctorMethod;
    if ((getSetDoctorMethod = emergencyAdminServiceGrpc.getSetDoctorMethod) == null) {
      synchronized (emergencyAdminServiceGrpc.class) {
        if ((getSetDoctorMethod = emergencyAdminServiceGrpc.getSetDoctorMethod) == null) {
          emergencyAdminServiceGrpc.getSetDoctorMethod = getSetDoctorMethod =
              io.grpc.MethodDescriptor.<ar.edu.itba.tp1g5.DoctorRequest, ar.edu.itba.tp1g5.DoctorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetDoctor"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ar.edu.itba.tp1g5.DoctorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ar.edu.itba.tp1g5.DoctorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new emergencyAdminServiceMethodDescriptorSupplier("SetDoctor"))
              .build();
        }
      }
    }
    return getSetDoctorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ar.edu.itba.tp1g5.DoctorRequest,
      ar.edu.itba.tp1g5.DoctorResponse> getCheckDoctorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CheckDoctor",
      requestType = ar.edu.itba.tp1g5.DoctorRequest.class,
      responseType = ar.edu.itba.tp1g5.DoctorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ar.edu.itba.tp1g5.DoctorRequest,
      ar.edu.itba.tp1g5.DoctorResponse> getCheckDoctorMethod() {
    io.grpc.MethodDescriptor<ar.edu.itba.tp1g5.DoctorRequest, ar.edu.itba.tp1g5.DoctorResponse> getCheckDoctorMethod;
    if ((getCheckDoctorMethod = emergencyAdminServiceGrpc.getCheckDoctorMethod) == null) {
      synchronized (emergencyAdminServiceGrpc.class) {
        if ((getCheckDoctorMethod = emergencyAdminServiceGrpc.getCheckDoctorMethod) == null) {
          emergencyAdminServiceGrpc.getCheckDoctorMethod = getCheckDoctorMethod =
              io.grpc.MethodDescriptor.<ar.edu.itba.tp1g5.DoctorRequest, ar.edu.itba.tp1g5.DoctorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckDoctor"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ar.edu.itba.tp1g5.DoctorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ar.edu.itba.tp1g5.DoctorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new emergencyAdminServiceMethodDescriptorSupplier("CheckDoctor"))
              .build();
        }
      }
    }
    return getCheckDoctorMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static emergencyAdminServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<emergencyAdminServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<emergencyAdminServiceStub>() {
        @java.lang.Override
        public emergencyAdminServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new emergencyAdminServiceStub(channel, callOptions);
        }
      };
    return emergencyAdminServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static emergencyAdminServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<emergencyAdminServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<emergencyAdminServiceBlockingStub>() {
        @java.lang.Override
        public emergencyAdminServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new emergencyAdminServiceBlockingStub(channel, callOptions);
        }
      };
    return emergencyAdminServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static emergencyAdminServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<emergencyAdminServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<emergencyAdminServiceFutureStub>() {
        @java.lang.Override
        public emergencyAdminServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new emergencyAdminServiceFutureStub(channel, callOptions);
        }
      };
    return emergencyAdminServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void addRoom(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.RoomResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddRoomMethod(), responseObserver);
    }

    /**
     */
    default void addDoctor(ar.edu.itba.tp1g5.DoctorRequest request,
        io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.DoctorResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddDoctorMethod(), responseObserver);
    }

    /**
     */
    default void setDoctor(ar.edu.itba.tp1g5.DoctorRequest request,
        io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.DoctorResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetDoctorMethod(), responseObserver);
    }

    /**
     */
    default void checkDoctor(ar.edu.itba.tp1g5.DoctorRequest request,
        io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.DoctorResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckDoctorMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service emergencyAdminService.
   */
  public static abstract class emergencyAdminServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return emergencyAdminServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service emergencyAdminService.
   */
  public static final class emergencyAdminServiceStub
      extends io.grpc.stub.AbstractAsyncStub<emergencyAdminServiceStub> {
    private emergencyAdminServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected emergencyAdminServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new emergencyAdminServiceStub(channel, callOptions);
    }

    /**
     */
    public void addRoom(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.RoomResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddRoomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addDoctor(ar.edu.itba.tp1g5.DoctorRequest request,
        io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.DoctorResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddDoctorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setDoctor(ar.edu.itba.tp1g5.DoctorRequest request,
        io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.DoctorResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetDoctorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void checkDoctor(ar.edu.itba.tp1g5.DoctorRequest request,
        io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.DoctorResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckDoctorMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service emergencyAdminService.
   */
  public static final class emergencyAdminServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<emergencyAdminServiceBlockingStub> {
    private emergencyAdminServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected emergencyAdminServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new emergencyAdminServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public ar.edu.itba.tp1g5.RoomResponse addRoom(com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddRoomMethod(), getCallOptions(), request);
    }

    /**
     */
    public ar.edu.itba.tp1g5.DoctorResponse addDoctor(ar.edu.itba.tp1g5.DoctorRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddDoctorMethod(), getCallOptions(), request);
    }

    /**
     */
    public ar.edu.itba.tp1g5.DoctorResponse setDoctor(ar.edu.itba.tp1g5.DoctorRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetDoctorMethod(), getCallOptions(), request);
    }

    /**
     */
    public ar.edu.itba.tp1g5.DoctorResponse checkDoctor(ar.edu.itba.tp1g5.DoctorRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckDoctorMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service emergencyAdminService.
   */
  public static final class emergencyAdminServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<emergencyAdminServiceFutureStub> {
    private emergencyAdminServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected emergencyAdminServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new emergencyAdminServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ar.edu.itba.tp1g5.RoomResponse> addRoom(
        com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddRoomMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ar.edu.itba.tp1g5.DoctorResponse> addDoctor(
        ar.edu.itba.tp1g5.DoctorRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddDoctorMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ar.edu.itba.tp1g5.DoctorResponse> setDoctor(
        ar.edu.itba.tp1g5.DoctorRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetDoctorMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ar.edu.itba.tp1g5.DoctorResponse> checkDoctor(
        ar.edu.itba.tp1g5.DoctorRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckDoctorMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD_ROOM = 0;
  private static final int METHODID_ADD_DOCTOR = 1;
  private static final int METHODID_SET_DOCTOR = 2;
  private static final int METHODID_CHECK_DOCTOR = 3;

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
        case METHODID_ADD_ROOM:
          serviceImpl.addRoom((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.RoomResponse>) responseObserver);
          break;
        case METHODID_ADD_DOCTOR:
          serviceImpl.addDoctor((ar.edu.itba.tp1g5.DoctorRequest) request,
              (io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.DoctorResponse>) responseObserver);
          break;
        case METHODID_SET_DOCTOR:
          serviceImpl.setDoctor((ar.edu.itba.tp1g5.DoctorRequest) request,
              (io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.DoctorResponse>) responseObserver);
          break;
        case METHODID_CHECK_DOCTOR:
          serviceImpl.checkDoctor((ar.edu.itba.tp1g5.DoctorRequest) request,
              (io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.DoctorResponse>) responseObserver);
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
          getAddRoomMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.google.protobuf.Empty,
              ar.edu.itba.tp1g5.RoomResponse>(
                service, METHODID_ADD_ROOM)))
        .addMethod(
          getAddDoctorMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              ar.edu.itba.tp1g5.DoctorRequest,
              ar.edu.itba.tp1g5.DoctorResponse>(
                service, METHODID_ADD_DOCTOR)))
        .addMethod(
          getSetDoctorMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              ar.edu.itba.tp1g5.DoctorRequest,
              ar.edu.itba.tp1g5.DoctorResponse>(
                service, METHODID_SET_DOCTOR)))
        .addMethod(
          getCheckDoctorMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              ar.edu.itba.tp1g5.DoctorRequest,
              ar.edu.itba.tp1g5.DoctorResponse>(
                service, METHODID_CHECK_DOCTOR)))
        .build();
  }

  private static abstract class emergencyAdminServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    emergencyAdminServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ar.edu.itba.tp1g5.emergencyAdminServiceModel.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("emergencyAdminService");
    }
  }

  private static final class emergencyAdminServiceFileDescriptorSupplier
      extends emergencyAdminServiceBaseDescriptorSupplier {
    emergencyAdminServiceFileDescriptorSupplier() {}
  }

  private static final class emergencyAdminServiceMethodDescriptorSupplier
      extends emergencyAdminServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    emergencyAdminServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (emergencyAdminServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new emergencyAdminServiceFileDescriptorSupplier())
              .addMethod(getAddRoomMethod())
              .addMethod(getAddDoctorMethod())
              .addMethod(getSetDoctorMethod())
              .addMethod(getCheckDoctorMethod())
              .build();
        }
      }
    }
    return result;
  }
}
