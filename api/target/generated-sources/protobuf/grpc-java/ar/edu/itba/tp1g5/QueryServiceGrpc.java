package ar.edu.itba.tp1g5;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.0)",
    comments = "Source: administration/queryService.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QueryServiceGrpc {

  private QueryServiceGrpc() {}

  public static final String SERVICE_NAME = "administration.QueryService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ar.edu.itba.tp1g5.QueryRequest,
      ar.edu.itba.tp1g5.QueryResponse> getQueryRoomsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryRooms",
      requestType = ar.edu.itba.tp1g5.QueryRequest.class,
      responseType = ar.edu.itba.tp1g5.QueryResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ar.edu.itba.tp1g5.QueryRequest,
      ar.edu.itba.tp1g5.QueryResponse> getQueryRoomsMethod() {
    io.grpc.MethodDescriptor<ar.edu.itba.tp1g5.QueryRequest, ar.edu.itba.tp1g5.QueryResponse> getQueryRoomsMethod;
    if ((getQueryRoomsMethod = QueryServiceGrpc.getQueryRoomsMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryRoomsMethod = QueryServiceGrpc.getQueryRoomsMethod) == null) {
          QueryServiceGrpc.getQueryRoomsMethod = getQueryRoomsMethod =
              io.grpc.MethodDescriptor.<ar.edu.itba.tp1g5.QueryRequest, ar.edu.itba.tp1g5.QueryResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryRooms"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ar.edu.itba.tp1g5.QueryRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ar.edu.itba.tp1g5.QueryResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryRooms"))
              .build();
        }
      }
    }
    return getQueryRoomsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ar.edu.itba.tp1g5.QueryRequest,
      ar.edu.itba.tp1g5.QueryResponse> getQueryWaitingRoomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryWaitingRoom",
      requestType = ar.edu.itba.tp1g5.QueryRequest.class,
      responseType = ar.edu.itba.tp1g5.QueryResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ar.edu.itba.tp1g5.QueryRequest,
      ar.edu.itba.tp1g5.QueryResponse> getQueryWaitingRoomMethod() {
    io.grpc.MethodDescriptor<ar.edu.itba.tp1g5.QueryRequest, ar.edu.itba.tp1g5.QueryResponse> getQueryWaitingRoomMethod;
    if ((getQueryWaitingRoomMethod = QueryServiceGrpc.getQueryWaitingRoomMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryWaitingRoomMethod = QueryServiceGrpc.getQueryWaitingRoomMethod) == null) {
          QueryServiceGrpc.getQueryWaitingRoomMethod = getQueryWaitingRoomMethod =
              io.grpc.MethodDescriptor.<ar.edu.itba.tp1g5.QueryRequest, ar.edu.itba.tp1g5.QueryResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryWaitingRoom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ar.edu.itba.tp1g5.QueryRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ar.edu.itba.tp1g5.QueryResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryWaitingRoom"))
              .build();
        }
      }
    }
    return getQueryWaitingRoomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ar.edu.itba.tp1g5.QueryRequest,
      ar.edu.itba.tp1g5.QueryResponse> getQueryCaresMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryCares",
      requestType = ar.edu.itba.tp1g5.QueryRequest.class,
      responseType = ar.edu.itba.tp1g5.QueryResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ar.edu.itba.tp1g5.QueryRequest,
      ar.edu.itba.tp1g5.QueryResponse> getQueryCaresMethod() {
    io.grpc.MethodDescriptor<ar.edu.itba.tp1g5.QueryRequest, ar.edu.itba.tp1g5.QueryResponse> getQueryCaresMethod;
    if ((getQueryCaresMethod = QueryServiceGrpc.getQueryCaresMethod) == null) {
      synchronized (QueryServiceGrpc.class) {
        if ((getQueryCaresMethod = QueryServiceGrpc.getQueryCaresMethod) == null) {
          QueryServiceGrpc.getQueryCaresMethod = getQueryCaresMethod =
              io.grpc.MethodDescriptor.<ar.edu.itba.tp1g5.QueryRequest, ar.edu.itba.tp1g5.QueryResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryCares"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ar.edu.itba.tp1g5.QueryRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ar.edu.itba.tp1g5.QueryResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QueryServiceMethodDescriptorSupplier("QueryCares"))
              .build();
        }
      }
    }
    return getQueryCaresMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static QueryServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QueryServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QueryServiceStub>() {
        @java.lang.Override
        public QueryServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QueryServiceStub(channel, callOptions);
        }
      };
    return QueryServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static QueryServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QueryServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QueryServiceBlockingStub>() {
        @java.lang.Override
        public QueryServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QueryServiceBlockingStub(channel, callOptions);
        }
      };
    return QueryServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static QueryServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QueryServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QueryServiceFutureStub>() {
        @java.lang.Override
        public QueryServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QueryServiceFutureStub(channel, callOptions);
        }
      };
    return QueryServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void queryRooms(ar.edu.itba.tp1g5.QueryRequest request,
        io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.QueryResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getQueryRoomsMethod(), responseObserver);
    }

    /**
     */
    default void queryWaitingRoom(ar.edu.itba.tp1g5.QueryRequest request,
        io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.QueryResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getQueryWaitingRoomMethod(), responseObserver);
    }

    /**
     */
    default void queryCares(ar.edu.itba.tp1g5.QueryRequest request,
        io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.QueryResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getQueryCaresMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service QueryService.
   */
  public static abstract class QueryServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return QueryServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service QueryService.
   */
  public static final class QueryServiceStub
      extends io.grpc.stub.AbstractAsyncStub<QueryServiceStub> {
    private QueryServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QueryServiceStub(channel, callOptions);
    }

    /**
     */
    public void queryRooms(ar.edu.itba.tp1g5.QueryRequest request,
        io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.QueryResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getQueryRoomsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryWaitingRoom(ar.edu.itba.tp1g5.QueryRequest request,
        io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.QueryResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getQueryWaitingRoomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryCares(ar.edu.itba.tp1g5.QueryRequest request,
        io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.QueryResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getQueryCaresMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service QueryService.
   */
  public static final class QueryServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<QueryServiceBlockingStub> {
    private QueryServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QueryServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public ar.edu.itba.tp1g5.QueryResponse queryRooms(ar.edu.itba.tp1g5.QueryRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getQueryRoomsMethod(), getCallOptions(), request);
    }

    /**
     */
    public ar.edu.itba.tp1g5.QueryResponse queryWaitingRoom(ar.edu.itba.tp1g5.QueryRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getQueryWaitingRoomMethod(), getCallOptions(), request);
    }

    /**
     */
    public ar.edu.itba.tp1g5.QueryResponse queryCares(ar.edu.itba.tp1g5.QueryRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getQueryCaresMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service QueryService.
   */
  public static final class QueryServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<QueryServiceFutureStub> {
    private QueryServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QueryServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ar.edu.itba.tp1g5.QueryResponse> queryRooms(
        ar.edu.itba.tp1g5.QueryRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getQueryRoomsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ar.edu.itba.tp1g5.QueryResponse> queryWaitingRoom(
        ar.edu.itba.tp1g5.QueryRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getQueryWaitingRoomMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ar.edu.itba.tp1g5.QueryResponse> queryCares(
        ar.edu.itba.tp1g5.QueryRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getQueryCaresMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_QUERY_ROOMS = 0;
  private static final int METHODID_QUERY_WAITING_ROOM = 1;
  private static final int METHODID_QUERY_CARES = 2;

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
        case METHODID_QUERY_ROOMS:
          serviceImpl.queryRooms((ar.edu.itba.tp1g5.QueryRequest) request,
              (io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.QueryResponse>) responseObserver);
          break;
        case METHODID_QUERY_WAITING_ROOM:
          serviceImpl.queryWaitingRoom((ar.edu.itba.tp1g5.QueryRequest) request,
              (io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.QueryResponse>) responseObserver);
          break;
        case METHODID_QUERY_CARES:
          serviceImpl.queryCares((ar.edu.itba.tp1g5.QueryRequest) request,
              (io.grpc.stub.StreamObserver<ar.edu.itba.tp1g5.QueryResponse>) responseObserver);
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
          getQueryRoomsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              ar.edu.itba.tp1g5.QueryRequest,
              ar.edu.itba.tp1g5.QueryResponse>(
                service, METHODID_QUERY_ROOMS)))
        .addMethod(
          getQueryWaitingRoomMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              ar.edu.itba.tp1g5.QueryRequest,
              ar.edu.itba.tp1g5.QueryResponse>(
                service, METHODID_QUERY_WAITING_ROOM)))
        .addMethod(
          getQueryCaresMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              ar.edu.itba.tp1g5.QueryRequest,
              ar.edu.itba.tp1g5.QueryResponse>(
                service, METHODID_QUERY_CARES)))
        .build();
  }

  private static abstract class QueryServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ar.edu.itba.tp1g5.QueryServiceModel.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("QueryService");
    }
  }

  private static final class QueryServiceFileDescriptorSupplier
      extends QueryServiceBaseDescriptorSupplier {
    QueryServiceFileDescriptorSupplier() {}
  }

  private static final class QueryServiceMethodDescriptorSupplier
      extends QueryServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    QueryServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (QueryServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new QueryServiceFileDescriptorSupplier())
              .addMethod(getQueryRoomsMethod())
              .addMethod(getQueryWaitingRoomMethod())
              .addMethod(getQueryCaresMethod())
              .build();
        }
      }
    }
    return result;
  }
}
