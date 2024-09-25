#!/bin/bash

target_dir="server/target"
client_dir="client/target"
temp_dir="tmp"

mvn clean package

mkdir -p "$temp_dir"

cp "$target_dir/tp1-g5-server-2024.2Q-bin.tar.gz" "$temp_dir/"
cp "$client_dir/tp1-g5-client-2024.2Q-bin.tar.gz" "$temp_dir/"
cd "$temp_dir"

# Server
tar -xzf "tp1-g5-server-2024.2Q-bin.tar.gz"
chmod +x tp1-g5-server-2024.2Q/run-server.sh
sed -i -e 's/\r$//' tp1-g5-server-2024.2Q/*.sh
rm "tp1-g5-server-2024.2Q-bin.tar.gz"

# Client
tar -xzf "tp1-g5-client-2024.2Q-bin.tar.gz"
chmod +x tp1-g5-client-2024.2Q/*-client.sh
sed -i -e 's/\r$//' tp1-g5-client-2024.2Q/*.sh
rm "tp1-g5-client-2024.2Q-bin.tar.gz"