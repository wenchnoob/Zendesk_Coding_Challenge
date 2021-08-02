package com.zendesk.ticketviewer;

import com.zendesk.ticketviewer.models.LinksTest;
import com.zendesk.ticketviewer.services.TicketServiceTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages("com.zendesk.ticketviewer")
public class TicketviewerApplicationTests {
}
