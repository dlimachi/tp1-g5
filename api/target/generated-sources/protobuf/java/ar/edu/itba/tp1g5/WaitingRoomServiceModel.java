// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: administration/waitingRoom.proto

package ar.edu.itba.tp1g5;

public final class WaitingRoomServiceModel {
  private WaitingRoomServiceModel() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_waitingroom_PatientRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_waitingroom_PatientRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_waitingroom_PatientResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_waitingroom_PatientResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n administration/waitingRoom.proto\022\013wait" +
      "ingroom\"4\n\016PatientRequest\022\023\n\013patientName" +
      "\030\001 \001(\t\022\r\n\005level\030\002 \001(\005\"M\n\017PatientResponse" +
      "\022\023\n\013patientName\030\001 \001(\t\022\r\n\005level\030\002 \001(\005\022\026\n\016" +
      "waitingPatient\030\003 \001(\0052\204\002\n\022WaitingRoomServ" +
      "ice\022L\n\017RegisterPatient\022\033.waitingroom.Pat" +
      "ientRequest\032\034.waitingroom.PatientRespons" +
      "e\022Q\n\024UpdateEmergencyLevel\022\033.waitingroom." +
      "PatientRequest\032\034.waitingroom.PatientResp" +
      "onse\022M\n\020CheckWaitingTime\022\033.waitingroom.P" +
      "atientRequest\032\034.waitingroom.PatientRespo" +
      "nseB.\n\021ar.edu.itba.tp1g5B\027WaitingRoomSer" +
      "viceModelP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_waitingroom_PatientRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_waitingroom_PatientRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_waitingroom_PatientRequest_descriptor,
        new java.lang.String[] { "PatientName", "Level", });
    internal_static_waitingroom_PatientResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_waitingroom_PatientResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_waitingroom_PatientResponse_descriptor,
        new java.lang.String[] { "PatientName", "Level", "WaitingPatient", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
