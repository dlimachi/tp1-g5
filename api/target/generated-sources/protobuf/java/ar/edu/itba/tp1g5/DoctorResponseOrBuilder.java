// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: administration/emergencyAdmin.proto

package ar.edu.itba.tp1g5;

public interface DoctorResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:administration.DoctorResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string doctorName = 1;</code>
   * @return The doctorName.
   */
  java.lang.String getDoctorName();
  /**
   * <code>string doctorName = 1;</code>
   * @return The bytes for doctorName.
   */
  com.google.protobuf.ByteString
      getDoctorNameBytes();

  /**
   * <code>int32 level = 2;</code>
   * @return The level.
   */
  int getLevel();

  /**
   * <code>string availability = 3;</code>
   * @return The availability.
   */
  java.lang.String getAvailability();
  /**
   * <code>string availability = 3;</code>
   * @return The bytes for availability.
   */
  com.google.protobuf.ByteString
      getAvailabilityBytes();

  /**
   * <code>int32 room = 4;</code>
   * @return The room.
   */
  int getRoom();
}
