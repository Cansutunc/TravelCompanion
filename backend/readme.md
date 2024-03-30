REST API hooks:

/api/v1/

    auth/

        /register

            @Post
            /user

            @Post
            /seller

        @Post
        /authenticate

        /Buyer

            @Get

    @Post
    energyPacket/

    energy/

        @Get
        procuced/

        @Get
        consumed/


    user/

        @get
        {email}/ (gets all data of a seller or a user that matches that email)

        sellerOrBuyer/

            @get
            {email} (gets if a user is a seller or a user)

    user/

        @Get 
        {id}/  (gets all data of a user)
    
            @Get
            battery/

            @Get
            batteryPercentage/

    seller/

        @Get 
        {id}/  (gets all data of a seller)

            @Put
            incrementPanels/

            @Put
            startSelling/

            @Put
            stopSelling/

            @Get
            battery/

            @Get
            batteryPercentage/

    @get
    time/

    @get
    weather/

    routes/

        @get
        getAll/

        @get
        getBySeller/

        @get
        getByBuyer/

        @get
        getSellerRevenue/

        @get
        getBuyerTotalExpenses/

        @get
        getBuyerTotalEnergyBuyed/

        @get
        getSellerTotalEnergySold/

        @post
        add/
    


    
    

