#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>

void handle_client(int c) {
    uint16_t path_len;
    // Receive path length
    if (recv(c, &path_len, sizeof(path_len), 0) <= 0) exit(1);
    path_len = ntohs(path_len);

    char path[1024] = {0};
    recv(c, path, path_len, 0);

    FILE *f = fopen(path, "rb");
    int32_t file_size = -1;

    if (!f) {
        int32_t net_size = htonl(file_size);
        send(c, &net_size, sizeof(net_size), 0);
    } else {
        // Get file size
        fseek(f, 0, SEEK_END);
        file_size = ftell(f);
        rewind(f);

        int32_t net_size = htonl(file_size);
        send(c, &net_size, sizeof(net_size), 0);

        // Send content
        char buffer[4096];
        size_t bytes_read;
        while ((bytes_read = fread(buffer, 1, sizeof(buffer), f)) > 0) {
            send(c, buffer, bytes_read, 0);
        }
        fclose(f);
    }
    close(c);
    exit(0); // Kill child process
}

int main() {
    int s = socket(AF_INET, SOCK_STREAM, 0);
    struct sockaddr_in server;
    memset(&server, 0, sizeof(server));
    server.sin_family = AF_INET;
    server.sin_port = htons(8888);
    server.sin_addr.s_addr = INADDR_ANY;

    bind(s, (struct sockaddr *)&server, sizeof(server));
    listen(s, 5);

    while (1) {
        struct sockaddr_in client;
        socklen_t l = sizeof(client);
        int c = accept(s, (struct sockaddr *)&client, &l);
        if (c < 0) continue;

        if (fork() == 0) {
            close(s); // Child doesn't need listening socket
            handle_client(c);
        }
        close(c); // Parent doesn't need client socket
    }
}
