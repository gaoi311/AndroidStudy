#!/usr/bin/env python3
# -*- coding: utf-8 -*-
# @Author : 麻花
# @Time : 2019/2/17 19:13
# @File : socket_server.py

import socketserver

class MyTCPHandler(socketserver.BaseRequestHandler):
    def handle(self):
        while True:
            try:
                self.data = self.request.recv(1024).strip()
                print("{} wrote:".format(self.client_address[0]))
                print(self.data.decode())
                self.request.send((self.data.decode() + '\n').encode())
                #self.request.send('\n'.encode())

            except ConnectionResetError as e:
                print('error', e)
                break

if __name__ == '__main__':
    HOST, PORT = "192.168.43.46", 38888
    #HOST, PORT = "localhost", 38888
    server = socketserver.ThreadingTCPServer((HOST, PORT), MyTCPHandler)
    server.serve_forever()
