// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: administration/emergencyAdmin.proto

package ar.edu.itba.tp1g5;

/**
 * Protobuf type {@code administration.DoctorRequest}
 */
public final class DoctorRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:administration.DoctorRequest)
    DoctorRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use DoctorRequest.newBuilder() to construct.
  private DoctorRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private DoctorRequest() {
    doctorName_ = "";
    availability_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new DoctorRequest();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return ar.edu.itba.tp1g5.emergencyAdminServiceModel.internal_static_administration_DoctorRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return ar.edu.itba.tp1g5.emergencyAdminServiceModel.internal_static_administration_DoctorRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            ar.edu.itba.tp1g5.DoctorRequest.class, ar.edu.itba.tp1g5.DoctorRequest.Builder.class);
  }

  public static final int DOCTORNAME_FIELD_NUMBER = 1;
  private volatile java.lang.Object doctorName_;
  /**
   * <code>string doctorName = 1;</code>
   * @return The doctorName.
   */
  @java.lang.Override
  public java.lang.String getDoctorName() {
    java.lang.Object ref = doctorName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      doctorName_ = s;
      return s;
    }
  }
  /**
   * <code>string doctorName = 1;</code>
   * @return The bytes for doctorName.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getDoctorNameBytes() {
    java.lang.Object ref = doctorName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      doctorName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int LEVEL_FIELD_NUMBER = 2;
  private int level_;
  /**
   * <code>int32 level = 2;</code>
   * @return The level.
   */
  @java.lang.Override
  public int getLevel() {
    return level_;
  }

  public static final int AVAILABILITY_FIELD_NUMBER = 3;
  private volatile java.lang.Object availability_;
  /**
   * <code>string availability = 3;</code>
   * @return The availability.
   */
  @java.lang.Override
  public java.lang.String getAvailability() {
    java.lang.Object ref = availability_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      availability_ = s;
      return s;
    }
  }
  /**
   * <code>string availability = 3;</code>
   * @return The bytes for availability.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getAvailabilityBytes() {
    java.lang.Object ref = availability_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      availability_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int ROOM_FIELD_NUMBER = 4;
  private int room_;
  /**
   * <code>int32 room = 4;</code>
   * @return The room.
   */
  @java.lang.Override
  public int getRoom() {
    return room_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(doctorName_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, doctorName_);
    }
    if (level_ != 0) {
      output.writeInt32(2, level_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(availability_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, availability_);
    }
    if (room_ != 0) {
      output.writeInt32(4, room_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(doctorName_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, doctorName_);
    }
    if (level_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, level_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(availability_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, availability_);
    }
    if (room_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, room_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof ar.edu.itba.tp1g5.DoctorRequest)) {
      return super.equals(obj);
    }
    ar.edu.itba.tp1g5.DoctorRequest other = (ar.edu.itba.tp1g5.DoctorRequest) obj;

    if (!getDoctorName()
        .equals(other.getDoctorName())) return false;
    if (getLevel()
        != other.getLevel()) return false;
    if (!getAvailability()
        .equals(other.getAvailability())) return false;
    if (getRoom()
        != other.getRoom()) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + DOCTORNAME_FIELD_NUMBER;
    hash = (53 * hash) + getDoctorName().hashCode();
    hash = (37 * hash) + LEVEL_FIELD_NUMBER;
    hash = (53 * hash) + getLevel();
    hash = (37 * hash) + AVAILABILITY_FIELD_NUMBER;
    hash = (53 * hash) + getAvailability().hashCode();
    hash = (37 * hash) + ROOM_FIELD_NUMBER;
    hash = (53 * hash) + getRoom();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static ar.edu.itba.tp1g5.DoctorRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static ar.edu.itba.tp1g5.DoctorRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static ar.edu.itba.tp1g5.DoctorRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static ar.edu.itba.tp1g5.DoctorRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static ar.edu.itba.tp1g5.DoctorRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static ar.edu.itba.tp1g5.DoctorRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static ar.edu.itba.tp1g5.DoctorRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static ar.edu.itba.tp1g5.DoctorRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static ar.edu.itba.tp1g5.DoctorRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static ar.edu.itba.tp1g5.DoctorRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static ar.edu.itba.tp1g5.DoctorRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static ar.edu.itba.tp1g5.DoctorRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(ar.edu.itba.tp1g5.DoctorRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code administration.DoctorRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:administration.DoctorRequest)
      ar.edu.itba.tp1g5.DoctorRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return ar.edu.itba.tp1g5.emergencyAdminServiceModel.internal_static_administration_DoctorRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return ar.edu.itba.tp1g5.emergencyAdminServiceModel.internal_static_administration_DoctorRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              ar.edu.itba.tp1g5.DoctorRequest.class, ar.edu.itba.tp1g5.DoctorRequest.Builder.class);
    }

    // Construct using ar.edu.itba.tp1g5.DoctorRequest.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      doctorName_ = "";

      level_ = 0;

      availability_ = "";

      room_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return ar.edu.itba.tp1g5.emergencyAdminServiceModel.internal_static_administration_DoctorRequest_descriptor;
    }

    @java.lang.Override
    public ar.edu.itba.tp1g5.DoctorRequest getDefaultInstanceForType() {
      return ar.edu.itba.tp1g5.DoctorRequest.getDefaultInstance();
    }

    @java.lang.Override
    public ar.edu.itba.tp1g5.DoctorRequest build() {
      ar.edu.itba.tp1g5.DoctorRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public ar.edu.itba.tp1g5.DoctorRequest buildPartial() {
      ar.edu.itba.tp1g5.DoctorRequest result = new ar.edu.itba.tp1g5.DoctorRequest(this);
      result.doctorName_ = doctorName_;
      result.level_ = level_;
      result.availability_ = availability_;
      result.room_ = room_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof ar.edu.itba.tp1g5.DoctorRequest) {
        return mergeFrom((ar.edu.itba.tp1g5.DoctorRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(ar.edu.itba.tp1g5.DoctorRequest other) {
      if (other == ar.edu.itba.tp1g5.DoctorRequest.getDefaultInstance()) return this;
      if (!other.getDoctorName().isEmpty()) {
        doctorName_ = other.doctorName_;
        onChanged();
      }
      if (other.getLevel() != 0) {
        setLevel(other.getLevel());
      }
      if (!other.getAvailability().isEmpty()) {
        availability_ = other.availability_;
        onChanged();
      }
      if (other.getRoom() != 0) {
        setRoom(other.getRoom());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              doctorName_ = input.readStringRequireUtf8();

              break;
            } // case 10
            case 16: {
              level_ = input.readInt32();

              break;
            } // case 16
            case 26: {
              availability_ = input.readStringRequireUtf8();

              break;
            } // case 26
            case 32: {
              room_ = input.readInt32();

              break;
            } // case 32
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }

    private java.lang.Object doctorName_ = "";
    /**
     * <code>string doctorName = 1;</code>
     * @return The doctorName.
     */
    public java.lang.String getDoctorName() {
      java.lang.Object ref = doctorName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        doctorName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string doctorName = 1;</code>
     * @return The bytes for doctorName.
     */
    public com.google.protobuf.ByteString
        getDoctorNameBytes() {
      java.lang.Object ref = doctorName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        doctorName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string doctorName = 1;</code>
     * @param value The doctorName to set.
     * @return This builder for chaining.
     */
    public Builder setDoctorName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      doctorName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string doctorName = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearDoctorName() {
      
      doctorName_ = getDefaultInstance().getDoctorName();
      onChanged();
      return this;
    }
    /**
     * <code>string doctorName = 1;</code>
     * @param value The bytes for doctorName to set.
     * @return This builder for chaining.
     */
    public Builder setDoctorNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      doctorName_ = value;
      onChanged();
      return this;
    }

    private int level_ ;
    /**
     * <code>int32 level = 2;</code>
     * @return The level.
     */
    @java.lang.Override
    public int getLevel() {
      return level_;
    }
    /**
     * <code>int32 level = 2;</code>
     * @param value The level to set.
     * @return This builder for chaining.
     */
    public Builder setLevel(int value) {
      
      level_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 level = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearLevel() {
      
      level_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object availability_ = "";
    /**
     * <code>string availability = 3;</code>
     * @return The availability.
     */
    public java.lang.String getAvailability() {
      java.lang.Object ref = availability_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        availability_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string availability = 3;</code>
     * @return The bytes for availability.
     */
    public com.google.protobuf.ByteString
        getAvailabilityBytes() {
      java.lang.Object ref = availability_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        availability_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string availability = 3;</code>
     * @param value The availability to set.
     * @return This builder for chaining.
     */
    public Builder setAvailability(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      availability_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string availability = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearAvailability() {
      
      availability_ = getDefaultInstance().getAvailability();
      onChanged();
      return this;
    }
    /**
     * <code>string availability = 3;</code>
     * @param value The bytes for availability to set.
     * @return This builder for chaining.
     */
    public Builder setAvailabilityBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      availability_ = value;
      onChanged();
      return this;
    }

    private int room_ ;
    /**
     * <code>int32 room = 4;</code>
     * @return The room.
     */
    @java.lang.Override
    public int getRoom() {
      return room_;
    }
    /**
     * <code>int32 room = 4;</code>
     * @param value The room to set.
     * @return This builder for chaining.
     */
    public Builder setRoom(int value) {
      
      room_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 room = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearRoom() {
      
      room_ = 0;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:administration.DoctorRequest)
  }

  // @@protoc_insertion_point(class_scope:administration.DoctorRequest)
  private static final ar.edu.itba.tp1g5.DoctorRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new ar.edu.itba.tp1g5.DoctorRequest();
  }

  public static ar.edu.itba.tp1g5.DoctorRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<DoctorRequest>
      PARSER = new com.google.protobuf.AbstractParser<DoctorRequest>() {
    @java.lang.Override
    public DoctorRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<DoctorRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<DoctorRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public ar.edu.itba.tp1g5.DoctorRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

