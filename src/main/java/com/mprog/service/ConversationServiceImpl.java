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
        log.info("greet user");
        localizePrintln("conversation.hello");

        log.info("start getting command");
        while (!commandGot) {
            String command = getCommand();

            if (command.equals("find-all")) {
                findAll();
            } else if (command.equals("find-by-id")) {
                findById();
            }
        }
        log.info("end getting command");

        localizePrintln("conversation.exit");
        log.info("exit from conversation");
    }

    private void findById() {
        log.info("start finding by id");
        localizePrint("conversation.get-id");
        log.info("get id from user");
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
        log.info("end of finding by id");
    }

    private void findAll() {
        log.info("start finding all");
        var tickets = ticketDao.findAll();
        localizePrintln("conversation.output");
        tickets.forEach(ioService::println);
        commandGot = true;
        log.info("end of finding all");
    }

    private String getCommand() {
        log.info("getting command");
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
