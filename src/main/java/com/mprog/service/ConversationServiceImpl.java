package com.mprog.service;

import com.mprog.dao.Dao;
import com.mprog.entity.Ticket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class ConversationServiceImpl implements ConversationService{

    private final MessageService messageService;
    private final IOService ioService;
    private final Dao<Ticket> ticketDao;
    private boolean commandGot = false;

    public void doConversation(){
        localizePrintln("conversation.hello");

        while (!commandGot) {
            String command = getCommand();

            if (command.equals("find-all")) {
                findAll();
            } else if (command.equals("find-by-id")) {
                findById();
            }
        }

        localizePrintln("conversation.exit");
    }

    private void findById() {
        localizePrint("conversation.get-id");
        var ticketId = ioService.nextInt();

        localizePrintln("conversation.output");

        commandGot = true;
        ticketDao.findById(ticketId).ifPresentOrElse(
                ioService::println,
                () -> {
                    localizePrint("conversation.no-such-ticket");
                    commandGot = false;
                }
        );
    }

    private void findAll() {
        var tickets = ticketDao.findAll();
        localizePrintln("conversation.output");
        tickets.forEach(ioService::println);
        commandGot = true;
    }

    private String getCommand() {
        localizePrint("conversation.enter-command");
        return ioService.nextLine().trim();
    }

    public void localizePrint(String code) {
        ioService.print(messageService.localize(code));
    }

    public void localizePrintln(String code){
        ioService.println(messageService.localize(code));
    }

}
