package ar.edu.itba.tp1g5;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.0)",
    comments = "Source: administration/emercengyCare.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class EmergencyCareServiceGrpc {

  private EmergencyCareServiceGrpc() {}

  public static final String SERVICE_NAME = "administration.EmergencyCareService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ar.edu.itba.tp1g5.EmergencyCareRequest,
      ar.edu.itba.tp1g5.EmergencyCareResponse> getStartEmergencyCareMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StartEmergencyCare",
      requestType = ar.edu.itba.tp1g5.EmergencyCareRequest.class,
      responseType = ar.edu.itba.tp1g5.EmergencyCareResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ar.edu.itba.tp1g5.EmergencyCareRequest,
      ar.edu.itba.tp1g5.EmergencyCareResponse> getStartEmergencyCareMethod() {
    io.grpc.MethodDescriptor<ar.edu.itba.tp1g5.EmergencyCareRequest, ar.edu.itba.tp1g5.EmergencyCareResponse> getStartEmergencyCareMethod;
    if ((getStartEmergencyCareMethod = EmergencyCareServiceGrpc.getStartEmergencyCareMethod) == null) {
      synchronized (EmergencyCareServiceGrpc.class) {
        if ((getStartEmergencyCareMethod = EmergencyCareServiceGrpc.getStartEmergencyCareMethod) == null) {
          EmergencyCareServiceGrpc.getStartEmergencyCareMethod = getStartEmergencyCareMethod =
              io.grpc.MethodDescriptor.<ar.edu.itba.tp1g5.EmergencyCareRequest, ar.edu.itba.tp1g5.EmergencyCareResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StartEmergencyCare"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ar.edu.itba.tp1g5.EmergencyCareRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ar.edu.itba.tp1g5.EmergencyCareResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EmergencyCareServiceMethodDescriptorSupplier("StartEmergencyCare"))
              .build();
        }
      }
    }
    return getStartEmergencyCareMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ar.edu.itba.tp1g5.EmergencyCareRequest,
      ar.edu.itba.tp1g5.EmergencyCareResponse> getEndEmergencyCareMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EndEmergencyCare",
      requestType = ar.edu.itba.tp1g5.EmergencyCareRequest.class,
      responseType = ar.edu.itba.tp1g5.EmergencyCareResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ar.edu.itba.tp1g5.EmergencyCareRequest,
      ar.edu.itba.tp1g5.EmergencyCareResponse> getEndEmergencyCareMethod() {
    io.grpc.MethodDescriptor<ar.edu.itba.tp1g5.EmergencyCareRequest, ar.edu.itba.tp1g5.EmergencyCareResponse> getEndEmergencyCareMethod;
    if ((getEndEmergencyCareMethod = EmergencyCareServiceGrpc.getEndEmergencyCareMethod) == null) {
      synchronized (EmergencyCareServiceGrpc.class) {
        if ((getEndEmergencyCareMethod = EmergencyCareServiceGrpc.getEndEmergencyCareMethod) == null) {
          EmergencyCareServiceGrpc.getEndEmergencyCareMethod = getEndEmergencyCareMethod =
              io.grpc.MethodDescriptor.<ar.edu.itba.tp1g5.EmergencyCareRequest, ar.edu.itba.tp1g5.EmergencyCareResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EndEmergencyCare"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ar.edu.itba.tp1g5.EmergencyCareRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ar.edu.itba.tp1g5.EmergencyCareResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EmergencyCareServiceMethodDescriptorSupplier("EndEmergencyCare"))
              .build();
        }
      }
    }
    return getEndEmergencyCareMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EmergencyCareServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EmergencyCareServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EmergencyCareServiceStub>() {
        @java.lang.Override
        public EmergencyCareServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EmergencyCareServiceStub(channel, callOptions);
        }
      };
    return EmergencyCareServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EmergencyCareServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EmergencyCareServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EmergencyCareServiceBlockingStub>() {
        @java.lang.Override
        public EmergencyCareServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EmergencyCareServiceBlockingStub(channel, callOptions);
        }
      };
    return EmergencyCareServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EmergencyCareServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EmergencyCareServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EmergencyCareServiceFutureStub>() {
        @java.lang.Override
        public EmergencyCareServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EmergencyCareServiceFutureStub(channel, callOptions);
        }
      };
    return EmergencyCareServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void startEmergencyCare(ar.edu.itba.tp1g5.EmergencyCareRequest request,
        io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.EmergencyCareResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStartEmergencyCareMethod(), responseObserver);
    }

    /**
     */
    default void endEmergencyCare(ar.edu.itba.tp1g5.EmergencyCareRequest request,
        io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.EmergencyCareResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEndEmergencyCareMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service EmergencyCareService.
   */
  public static abstract class EmergencyCareServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return EmergencyCareServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service EmergencyCareService.
   */
  public static final class EmergencyCareServiceStub
      extends io.grpc.stub.AbstractAsyncStub<EmergencyCareServiceStub> {
    private EmergencyCareServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EmergencyCareServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EmergencyCareServiceStub(channel, callOptions);
    }

    /**
     */
    public void startEmergencyCare(ar.edu.itba.tp1g5.EmergencyCareRequest request,
        io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.EmergencyCareResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getStartEmergencyCareMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void endEmergencyCare(ar.edu.itba.tp1g5.EmergencyCareRequest request,
        io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.EmergencyCareResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEndEmergencyCareMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service EmergencyCareService.
   */
  public static final class EmergencyCareServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<EmergencyCareServiceBlockingStub> {
    private EmergencyCareServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EmergencyCareServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EmergencyCareServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public ar.edu.itba.tp1g5.EmergencyCareResponse startEmergencyCare(ar.edu.itba.tp1g5.EmergencyCareRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getStartEmergencyCareMethod(), getCallOptions(), request);
    }

    /**
     */
    public ar.edu.itba.tp1g5.EmergencyCareResponse endEmergencyCare(ar.edu.itba.tp1g5.EmergencyCareRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEndEmergencyCareMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service EmergencyCareService.
   */
  public static final class EmergencyCareServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<EmergencyCareServiceFutureStub> {
    private EmergencyCareServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EmergencyCareServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EmergencyCareServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ar.edu.itba.tp1g5.EmergencyCareResponse> startEmergencyCare(
        ar.edu.itba.tp1g5.EmergencyCareRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getStartEmergencyCareMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ar.edu.itba.tp1g5.EmergencyCareResponse> endEmergencyCare(
        ar.edu.itba.tp1g5.EmergencyCareRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEndEmergencyCareMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_START_EMERGENCY_CARE = 0;
  private static final int METHODID_END_EMERGENCY_CARE = 1;

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
        case METHODID_START_EMERGENCY_CARE:
          serviceImpl.startEmergencyCare((ar.edu.itba.tp1g5.EmergencyCareRequest) request,
              (io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.EmergencyCareResponse>) responseObserver);
          break;
        case METHODID_END_EMERGENCY_CARE:
          serviceImpl.endEmergencyCare((ar.edu.itba.tp1g5.EmergencyCareRequest) request,
              (io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.EmergencyCareResponse>) responseObserver);
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
          getStartEmergencyCareMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              ar.edu.itba.tp1g5.EmergencyCareRequest,
              ar.edu.itba.tp1g5.EmergencyCareResponse>(
                service, METHODID_START_EMERGENCY_CARE)))
        .addMethod(
          getEndEmergencyCareMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              ar.edu.itba.tp1g5.EmergencyCareRequest,
              ar.edu.itba.tp1g5.EmergencyCareResponse>(
                service, METHODID_END_EMERGENCY_CARE)))
        .build();
  }

  private static abstract class EmergencyCareServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EmergencyCareServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ar.edu.itba.tp1g5.emergencyCareServiceModel.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("EmergencyCareService");
    }
  }

  private static final class EmergencyCareServiceFileDescriptorSupplier
      extends EmergencyCareServiceBaseDescriptorSupplier {
    EmergencyCareServiceFileDescriptorSupplier() {}
  }

  private static final class EmergencyCareServiceMethodDescriptorSupplier
      extends EmergencyCareServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    EmergencyCareServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (EmergencyCareServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EmergencyCareServiceFileDescriptorSupplier())
              .addMethod(getStartEmergencyCareMethod())
              .addMethod(getEndEmergencyCareMethod())
              .build();
        }
      }
    }
    return result;
  }
}
