#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

int main() {
    int c = socket(AF_INET, SOCK_STREAM, 0);
    struct sockaddr_in server;
    memset(&server, 0, sizeof(server));
    server.sin_family = AF_INET;
    server.sin_port = htons(8888);
    server.sin_addr.s_addr = inet_addr("127.0.0.1");

    if (connect(c, (struct sockaddr *)&server, sizeof(server)) < 0) {
        printf("Connection failed.\n");
        return 1;
    }

    char cmd[1024];
    printf("Enter command: ");
    fgets(cmd, sizeof(cmd), stdin);
    cmd[strcspn(cmd, "\n")] = 0; 

    // Send length-prefixed command
    uint16_t cmd_len = htons(strlen(cmd));
    send(c, &cmd_len, sizeof(cmd_len), 0);
    send(c, cmd, strlen(cmd), 0);

    // Receive exit code
    int32_t exit_code;
    recv(c, &exit_code, sizeof(exit_code), 0);
    exit_code = ntohl(exit_code);

    // Receive output length
    uint32_t out_len;
    recv(c, &out_len, sizeof(out_len), 0);
    out_len = ntohl(out_len);

    char output[8192] = {0};
    if (out_len > 0) {
        // MSG_WAITALL ensures we get the full payload before printing[cite: 1]
        recv(c, output, out_len, MSG_WAITALL); 
    }

    printf("\n--- Server Response ---\n");
    printf("Exit Code: %d\n", exit_code);
    printf("Output:\n%s\n", out_len > 0 ? output : "(No output)");
    
    close(c);
    return 0;
}
