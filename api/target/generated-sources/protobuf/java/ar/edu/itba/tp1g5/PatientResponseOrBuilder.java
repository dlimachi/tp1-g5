// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: administration/waitingRoom.proto

package ar.edu.itba.tp1g5;

public interface PatientResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:waitingroom.PatientResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string patientName = 1;</code>
   * @return The patientName.
   */
  java.lang.String getPatientName();
  /**
   * <code>string patientName = 1;</code>
   * @return The bytes for patientName.
   */
  com.google.protobuf.ByteString
      getPatientNameBytes();

  /**
   * <code>int32 level = 2;</code>
   * @return The level.
   */
  int getLevel();

  /**
   * <code>int32 waitingPatient = 3;</code>
   * @return The waitingPatient.
   */
  int getWaitingPatient();
}
