<form action="${pageContext.request.contextPath}/dc" method="post">
    <div class="field">
        Host:<br />

        <input type="text" name="dc.name" value=""/>
    </div>

    <div class="field">
        Port:<br />

        <input type="text" name="dc.port" value=""/>
    </div>

    <div class="field">
        Username:<br />

        <input type="text" name="dc.username" value=""/>
    </div>

    <div class="field">
        Password:<br />

        <input type="text" name="dc.password" value=""/>
    </div>

    <div class="actions">
        <button type="submit">send</button>
    </div>
</form>

<a href="${pageContext.request.contextPath}/products">Back</a>
