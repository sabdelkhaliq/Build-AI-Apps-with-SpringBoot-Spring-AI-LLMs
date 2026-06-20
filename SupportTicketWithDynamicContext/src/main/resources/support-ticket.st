Route this support ticket to the correct team.

{context}

Example:

Ticket:
When I open the invoice page, I get a 500 error and cannot download my invoice.

Answer:
Team: BILLING_TEAM
Reason: The issue happens inside the invoice page, so it belongs to the billing team even though it shows a 500 error.

Now route this ticket:

Ticket:
{ticket}

Return exactly:
Team: <BILLING_TEAM or TECHNICAL_TEAM>
Reason: <one short sentence>